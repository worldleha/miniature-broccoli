package com.miniaturebroccoli.utils;

import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.pojo.Audience;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.Base64Util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenUtil {
    /**
     * 授权标题键
     **/
    public static final String AUTH_HEADER_KEY = "Authorization";
    /**
     * Token前缀
     **/
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (ExpiredJwtException eje) {
            log.error("Token过期", eje);
            throw new CustomException(ResultData.fail(500, "Token过期"));
        } catch (Exception e) {
            log.error("token解析异常", e);
            throw new CustomException(ResultData.fail(500, "token解析异常"));
        }
    }

    /**
     * 构建jwt
     */
    public static String createJWT(String userId, String username, String role, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            //userId是重要信息，进行加密下
            String encryId = Base64Util.encode(userId);
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", role)
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId()) // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())  // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())  // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
                log.info("JWT过期时间" + exp);
                log.info("JWT生效的开始时间" + now);
            }
            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new CustomException(ResultData.fail(500, "签名失败"));
        }
    }

    /**
     * 从token中获取用户名
     */
    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     */
    public static String getUserId(String token, String base64Security) {
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return Base64Util.decode(userId);
    }

    /**
     * 是否已过期
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
}
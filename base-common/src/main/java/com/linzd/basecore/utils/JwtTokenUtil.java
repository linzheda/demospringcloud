package com.linzd.basecore.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 jwt token的生成和校验
 *
 * @author Lorenzo Lin
 * @created 2019年10月12日 11:41
 */
public class JwtTokenUtil {
    //过期时间
    public static final long EXPIRE_TIME = 30 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "privateKey";
    //登录token前缀
    public static final String LOGIN_TOKEN_PREFIX = "pc_login:";

    /**
     * 生成签名，30分钟过期
     * @param **username**
     * @param **password**
     * @return
     */
    public static String sign(Long userId) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static Map<String,Object> verify(String token){
        Map<String, Object> result = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Long userId = jwt.getClaim("userId").asLong();
            //id
            result.put("userId",userId);
            //过期时间
            result.put("exp",jwt.getExpiresAt());
            return result;
        } catch (Exception e){
            return null;
        }
    }

    /**
     *  根据token获取用户id
     * @param **token**
     * @return
     */
    public static Long getUserIdByToken(String token){
        Map<String,Object> result=  verify(token);
        return  result!=null?(Long)result.get("userId"):null;
    }

}

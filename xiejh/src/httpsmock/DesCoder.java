package httpsmock;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesCoder {
	/**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    
    /**
     * 初始化密钥
     *
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initSecretKey(SecureRandom random) throws Exception{
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        //初始化此密钥生成器，使其具有确定的密钥大小
        kg.init(random);
        //生成一个密钥
        SecretKey  secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
    /**
     * 转换密钥
     *
     * @param key	二进制密钥
     * @return Key	密钥
     * @throws Exception
     */
    public static Key toKey(byte[] key) throws Exception{
        //实例化DES密钥规则
        DESKeySpec dks = new DESKeySpec(key);
        //实例化密钥工厂
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        //生成密钥
        SecretKey  secretKey = skf.generateSecret(dks);
        return secretKey;
    }
}

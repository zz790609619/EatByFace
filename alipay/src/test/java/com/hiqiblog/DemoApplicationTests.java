package com.hiqiblog;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Cipher;
import java.io.Console;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  /** 发送html格式Email */
  @Test
  public void testEmail() throws Exception {
    //      ListNode l1 = new ListNode(2);
    //      ListNode l1_1 = new ListNode(4);
    //      ListNode l1_2 = new ListNode(3);
    //
    //      l1.next = l1_1;
    //      l1_1.next = l1_2;
    //
    //      ListNode l2 = new ListNode(5);
    //      ListNode l2_1 = new ListNode(6);
    //      ListNode l2_2 = new ListNode(4);
    //
    //      l2.next = l2_1;
    //      l2_1.next=12_2;
      //    int [] s={2,1};
    //      int reslut=findKthLargest(s,2);


      //初始化密钥
      //生成密钥对
      Map<String, Object> keyMap = initKey();
      //公钥
      byte[] publicKey = getPublicKey(keyMap);

      //私钥
      byte[] privateKey = getPrivateKey(keyMap);
      System.out.println("公钥：/n" + Base64.encodeBase64String(publicKey));
      System.out.println("私钥：/n" + Base64.encodeBase64String(privateKey));

      System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
      String str = "RSA密码交换算法";
      System.out.println("/n===========甲方向乙方发送加密数据==============");
      System.out.println("原文:" + str);
      //甲方进行数据的加密
      byte[] code1 = encryptByPrivateKey(str.getBytes(), privateKey);
      System.out.println("加密后的数据：" + Base64.encodeBase64String(code1));
      System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
      //乙方进行数据的解密
      byte[] decode1 = decryptByPublicKey(code1, publicKey);
      System.out.println("乙方解密后的数据：" + new String(decode1) + "/n/n");

      System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");

      str = "乙方向甲方发送数据RSA算法";

      System.out.println("原文:" + str);

      //乙方使用公钥对数据进行加密
      byte[] code2 =encryptByPublicKey(str.getBytes(), publicKey);
      System.out.println("===========乙方使用公钥对数据进行加密==============");
      System.out.println("加密后的数据：" + Base64.encodeBase64String(code2));

      System.out.println("=============乙方将数据传送给甲方======================");
      System.out.println("===========甲方使用私钥对数据进行解密==============");

      //甲方使用私钥对数据进行解密
      byte[] decode2 = decryptByPrivateKey(code2, privateKey);

      System.out.println("甲方解密后的数据：" + new String(decode2));
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       int result=0;
       ListNode resultNode=new ListNode(0);
       ListNode currNode=resultNode, num1=l1, num2=l2;
      while(num1!=null || num2!=null){
          //1.判断当前节点是否为空,如果为空则赋值为0
          int l1num=(num1==null)? 0:num1.val;
          int l2num=(num2==null)? 0:num2.val;
          int sum=l1num+l2num+result;
          result=sum/10;
          currNode.next=new ListNode(sum%10);
          currNode=currNode.next;
          if(num1!=null) num1=num1.next;
          if(num2!=null)num2=num2.next;

      }
      if (result > 0) {
          currNode.next = new ListNode(result);
      }
      return resultNode.next;

//      ListNode dummyHead = new ListNode(0);
//      ListNode p = l1, q = l2, curr = dummyHead;
//      int carry = 0;
//      while (p != null || q != null) {
//          int x = (p != null) ? p.val : 0;
//          int y = (q != null) ? q.val : 0;
//          int sum = carry + x + y;
//          carry = sum / 10;
//          curr.next = new ListNode(sum % 10);
//          curr = curr.next;
//          if (p != null) p = p.next;
//          if (q != null) q = q.next;
//      }
//      if (carry > 0) {
//          curr.next = new ListNode(carry);
//      }
//      return dummyHead.next;

  }
  public int  findKthLargest(int[] nums, int k){
      Map<Integer,Integer> map=new HashMap<>();
      int temp;
      for(int i=0;i<=nums.length-1;i++){
          for (int j=0;j<nums.length-1-i;j++){
              if (nums[j] < nums[j+1]) {
                  temp = nums[j];
                  nums[j] = nums[j+1];
                  nums[j+1] = temp;
              }
          }
      }
      for(int i=0;i<nums.length-1;i++){
          map.put(i+1,nums[i]);
      }
      if(nums.length>1){
          for(int i=0;i<nums.length-1;i++){
              map.put(i+1,nums[i]);
          }
      }
      else{
          map.put(k,nums[0]);
      }
      return map.get(k);
  }
    public int findKthLargests(int[] nums, int k) {
        // init heap 'the smallest element first'PriorityQueue
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }
    public int numJewelsInStones(String J, String S) {
      char [] j=J.toCharArray();
      char  [] s=S.toCharArray();
      int count=0;
      for (char i :j){
          for (char z:s){
              if(i==z){
                  count++;
              }
          }
      }
      return count;
    }


    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";


    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;
    //公钥
    private static final String PUBLIC_KEY = "RSAPublicKey";

    //私钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 初始化密钥对
     *
     * @return Map 甲方密钥的Map
     */
    public static Map<String, Object> initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在map中
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }


    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

}


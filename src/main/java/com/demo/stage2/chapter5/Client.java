/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter5;

/**
 * @author yan.zhang
 * @date 2019/11/4 21:04
 */

/**
 * 读写锁Demo
 * 1.read other thread will enter to waitSet
 * 2.read write 锁分离
 * <p>
 * read read 并行化
 * read write 串行化
 * write write 不允许
 */
public class Client {

    public static void main(String[] args) {

        Gate gate = new Gate();
        User u1 = new User("baoBao", "beiJin", gate);
        User u2 = new User("beiBei", "shangHai", gate);
        User u3 = new User("faLao", "guangZhou", gate);

        u1.start();
        u2.start();
        u3.start();
    }
}

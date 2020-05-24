package cww523;
public class Mian {
    private static int sum;
    public static void main(String[] args) {
        for (int i = 0; i <=5; i++) { //5个面包师傅
            //面包师傅和仓库
            new Thread(()->{
                while (true){
                      synchronized (Mian.class){
                              if(sum+3>100){
                                  try {
                                      Mian.class.wait();     //库存大于100.进行等待
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                              }else {
                                  sum+=3;
                                  System.out.println(Thread.currentThread().getName()+ "生产了面包，库存:"+ sum);
                                  try {
                                      Thread.sleep(1000);
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                                  Mian.class.notify();     //唤醒被阻塞i的
                              }
                          try {
                              Thread.sleep(200);
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                      }

                }

            }).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                 while (true){
                     synchronized (Mian.class){
                         if(sum==0){
                             try {
                                 Mian.class.wait();     //库存大于100.进行等待
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                         }else {
                             sum--;
                             System.out.println(Thread.currentThread().getName()+ "生产了面包，库存:"+ sum);
                             try {
                                 Thread.sleep(1000);
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                             Mian.class.notify();     //唤醒被阻塞i的
                         }
                     }
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
            }).start();
        }

    }
}

package com.zzx;

/**
 * Created by Mr.John on 2018/10/23 17:58.
 **/
public class BobLi {
    public static void main(String[] args) {
        boolean b[] = new boolean[]{false, false,true,false,true,false, true};

        boolean bb = true;
        int t = 0;
        int f = 0;
        if(b[0]==true||b[b.length-1]==true){
            t+=1;
        }
        if(b[0]==false||b[b.length-1]==false){
            f+=1;
        }
        for (int i = 0; i < b.length; i++) {
            if (i>0&&i<b.length-1&&b[i] == true&&b[i-1]==false&&b[i+1]!=true) {
                t += 1;
            }else if(i>0&&i<b.length-1&&b[i] == false&&b[i-1]==true&&b[i+1]!=false){
                f+=1;
            }
        }
        System.out.println(t);
        System.out.println(f);


    }
}

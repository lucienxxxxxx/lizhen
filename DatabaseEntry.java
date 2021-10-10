import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DatabaseEntry
 * @author: lucine
 * @Description 输出理正标准接口数据
 * @date 2021/9/9 19:30
 * @Version 1.0版本
 */
public class DatabaseEntry {

    public static void main(String[] args) {
        /**
         * 输入钻孔数据
         * 钻孔编号 勘探点类型 X坐标 Y坐标 偏移量 孔口标高 水面标高 勘探深度 探井深度(勘探深度) 钻孔直径 勘探开始日期 勘探结束日期
         */
        String[] zk_array={"ZK-1","鉴别孔","498608.99","197114.43","11","71.29","71.29","40","40","12","2021/12/11","2022/10/11"};

        /**
         * 输入土层数据
         * 岩土名称 层底深度 地层厚度 主层编号 亚层编号  次亚层编号
         * 地质时代 地质成因 颜色 密实度 湿度 可塑性 浑圆度 均匀性
         * 风化程度 岩层倾向 岩层倾角 矿物成分 结构构造 包含物 气味
         * 描述 完整程度 坚硬程度 破碎程度 节理发育 节理间距
         */
        String[] tc_array={"填土","1","1","0","0","0",
                "Q4","m","黄","中密","湿","可塑","圆","均匀",
                "全风化","12.0","12.0","","","云母","",
                "","","","","",""};

        /**
         *输入标贯数据
         *试验点的底深度 标贯类型 特征值 杆长 一阵击数的长度 一阵击数 标贯击数 标贯修正系数（中间结果） 修正后的标贯击数 修正否 参与否
         */
        String[] bg_array={"1","","","12","12","12","12","1","1","",""};


        //多组数据
        String[] zk_array1={"ZK-2","鉴别孔","498608.99","197114.43","11","71.29","71.29","40","40","12","2021/12/11","2022/10/11"};
        String[] tc_array1={"粘土","1","1","0","0","0",
                "Q4","m","黄","中密","湿","可塑","圆","均匀",
                "全风化","12.0","12.0","","","云母","",
                "","","","","",""};
        String[] bg_array1={"1","","","11","11","11","11","1","1","",""};
        String data1=format(zk_array1,"#ZK#")+"\r\n"+format(tc_array1,"#TC#")+"\r\n"+format(bg_array1,"#BG#");


        //对数组进行格式化
        String ZK =format(zk_array,"#ZK#");
        String TC =format(tc_array,"#TC#");
        String BG =format(bg_array,"#BG#");
        //拼接一个钻孔的数据
        String data=ZK+"\r\n"+TC+"\r\n"+BG;



        //将一个钻孔数据添加到集合中，可以添加多个数据
        List<String> output=new ArrayList<>();
        output.add(data);
        output.add(data1);


        //输出理正标准接口数据
        exportData(output);

    }

    /**
     * 格式化数组
     * @param array 输入数组
     * @param type 表的类型
     * @return
     */
    public static String format(String[] array,String type){
        String data=type;
        for (int i = 0; i < array.length-1; i++) {
            data+=array[i]+"\t";
        }
//        System.out.println(data);
        return data;
    }

    /**
     * 导出理正txt接口文件
     * @param datas  最终数据集合
     */
    public static void exportData(List<String> datas){
        // 生成的文件路径
        String path = "E:\\" + "理正数据库录入数据" + ".txt";
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            // write 解决中文乱码问题，不能使用utf-8，否则理正数据库乱码
            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "GB2312");

            BufferedWriter bw = new BufferedWriter(fw);
            for (String data : datas) {
                System.out.println(data);
                bw.write(data);
                bw.write("\r\n");
                bw.flush();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

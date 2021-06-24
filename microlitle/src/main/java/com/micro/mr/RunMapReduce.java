package com.micro.mr;

import com.micro.hdfs.IHDFSDao;
import com.micro.hdfs.impl.HDFSDaoImpl;

/**
 * 执行MapReduce
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/17
 **/
public class RunMapReduce {
        public static void beforrun() throws  Exception{
            //创建hdfs访问接口对象
            IHDFSDao ihdfsDao = new HDFSDaoImpl();
            try{
                ihdfsDao.DeleteFile("/data/area");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/communication2018");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/communication2019");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/consumption2018");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/consumption2019");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/entertainment");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/index");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/learn2018");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/learn2019");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/life");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/phone_board");
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ihdfsDao.DeleteFile("/data/phone_price");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public static void run() throws Exception{
            beforrun();
            //统计数据
            try{
                AreaDriver areaDriver =new AreaDriver();
                areaDriver.run();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                CommunicationDriver communicationDriver= new CommunicationDriver();
                communicationDriver.run2018();
                communicationDriver.run2019();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ConsumptionDriver consumptionDriver=new ConsumptionDriver();
                consumptionDriver.run2018();
                consumptionDriver.run2019();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                EntertainmentDriver entertainmentDriver=new EntertainmentDriver();
                entertainmentDriver.run();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                IndexDriver indexDriver=new IndexDriver();
                indexDriver.run();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                LearnDriver learnDriver=new LearnDriver();
                learnDriver.run2018();
                learnDriver.run2019();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                LifeDriver lifeDriver=new LifeDriver();
                lifeDriver.run();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                Phone_boardDriver phone_boardDriver=new Phone_boardDriver();
                phone_boardDriver.run();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                Phone_priceDriver phone_priceDriver=new Phone_priceDriver();
                phone_priceDriver.run();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}

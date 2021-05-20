package com.imp.service;

import com.imp.mapper.MuseumMapper;
import com.imp.model.MuseumModel;
import com.imp.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 博物馆表 Service
 */
@Service
public class MuseumService {

    @Autowired
    private MuseumMapper museumMapper;

    /**
     * 按主键删除
     */
    public boolean deleteById(Long musId) {
        return musId != null && this.museumMapper.deleteById(musId) >= 0;
    }

    /**
     * 按主键删除
     */
    public boolean deleteByIds(List<Long> ids) {
        return ids != null && (ids.isEmpty() || this.museumMapper.deleteByIds(ids) > 0);
    }

    /**
     * 插入数据
     */
    public boolean insert(MuseumModel model) {
        return model != null && this.museumMapper.insert(model) == 1;
    }

    /**
     * 插入多条数据
     */
    public boolean insertList(List<MuseumModel> models) {
        if (models == null) {
            return false;
        }
        if (models.isEmpty()) {
            return true;
        }
        if (models.size() > 100) {
            for (List<MuseumModel> list : ListUtil.getSubList(models, 100)) {
                this.museumMapper.insertList(list);
            }
        } else {
            return this.museumMapper.insertList(models) > 0;
        }
        return true;
    }

    /**
     * 插入数据,字段为空时不插入
     */
    public boolean insertSelective(MuseumModel model) {
        return model != null && this.museumMapper.insertSelective(model) == 1;
    }

    /**
     * 按主键选择
     */
    public MuseumModel selectById(Long musId) {
        return musId == null ? null : this.museumMapper.selectById(musId);
    }

    /**
     * 按模型选择
     */
    public List<MuseumModel> selectByModel(MuseumModel model) {
        return model == null ? new ArrayList<>(0) : this.museumMapper.selectByModel(model);
    }

    /**
     * 按主键更新,字段为空时不更新
     */
    public boolean updateByIdSelective(MuseumModel model) {
        return !(model == null || model.getMusId() == null) && this.museumMapper.updateByIdSelective(model) == 1;
    }



    /**
     * 按主键更新
     */
    public boolean updateById(MuseumModel model) {
        return !(model == null || model.getMusId() == null) && this.museumMapper.updateById(model) == 1;
    }

    /**
     * 按主键删除
     */
    public boolean deleteById(MuseumModel model) {
        return !(model == null || model.getMusId() == null) && this.museumMapper.deleteById(model.getMusId()) >= 0;
    }

    /**
     * 插入或更新
     */
    public boolean insertOrUpdate(MuseumModel model) {
        if (model == null) {
            return false;
        }
        if (model.getMusId() == null) {
            return this.museumMapper.insert(model) == 1;
        }
        return this.museumMapper.updateById(model) == 1;
    }


//    //地图位置标签
//    HashMap<String, LatLng> latLngHashMap=new HashMap<String, LatLng>();
//    //地图名字hashmap
//    HashMap<String,String> mid2Name = new HashMap<String, String>();
//
//    public void insertLatlng(){
//        updateLatlng();
//        Iterator<Map.Entry<String,String>> iterator =  mid2Name.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String,String> entry = iterator.next();
//            MuseumModel model = new MuseumModel();
//            model.setMusName(entry.getValue());
//            List<MuseumModel> list = this.selectByModel(model);
//            if(list.isEmpty()){
//               continue;
//            }
//            model = list.get(0);
//            LatLng latLng = latLngHashMap.get(entry.getKey());
//            model.setMusLat(latLng.getLat());
//            model.setMusLng(latLng.getLng());
//            this.updateById(model);
//        }
//
//    }
//
//
//    public void updateLatlng(){
//        mid2Name.put("mMarket_1","故宫博物馆");
//        mid2Name.put("mMarket_2","中国科学技术馆");
//        mid2Name.put("mMarket_3","中国地质博物馆");
//        mid2Name.put("mMarket_4","中国人民革命军事博物馆");
//        mid2Name.put("mMarket_5","中国航空博物馆");
//        mid2Name.put("mMarket_6","北京鲁迅博物馆");
//        mid2Name.put("mMarket_7","首都博物馆");
//        mid2Name.put("mMarket_8","北京自然博物馆");
//        mid2Name.put("mMarket_9","中国人民抗日战争纪念馆");
//        mid2Name.put("mMarket_10","北京天文馆");
//        mid2Name.put("mMarket_11","周口店猿人遗址博物馆");
//        mid2Name.put("mMarket_12","中国国家博物馆");
//        mid2Name.put("mMarket_13","中国农业博物馆");
//        mid2Name.put("mMarket_14","文化部恭王府博物馆");
//        mid2Name.put("mMarket_15","天津博物馆");
//        mid2Name.put("mMarket_16","天津自然博物馆");
//        mid2Name.put("mMarket_17","周恩来邓颖超纪念馆");
//        mid2Name.put("mMarket_18","河北博物院");
//        mid2Name.put("mMarket_19","西柏坡纪念馆");
//        mid2Name.put("mMarket_20","邯郸市博物馆");
//        mid2Name.put("mMarket_21","山西博物院");
//        mid2Name.put("mMarket_22","中国煤炭博物馆");
//        mid2Name.put("mMarket_23","八路军太行纪念馆");
//        mid2Name.put("mMarket_24","内蒙古博物院");
//        mid2Name.put("mMarket_25","鄂尔多斯博物馆");
//        mid2Name.put("mMarket_26","辽宁省博物馆");
//        mid2Name.put("mMarket_27","“九·一八”历史博物馆");
//        mid2Name.put("mMarket_28","抗美援朝纪念馆");
//        mid2Name.put("mMarket_29","旅顺博物馆");
//        mid2Name.put("mMarket_30","沈阳故宫博物院");
//        mid2Name.put("mMarket_31","大连现代博物馆");
//        mid2Name.put("mMarket_32","吉林省自然博物馆");
//        mid2Name.put("mMarket_33","吉林省博物院");
//        mid2Name.put("mMarket_34","伪满皇宫博物院");
//        mid2Name.put("mMarket_35","东北烈士纪念馆");
//        mid2Name.put("mMarket_36","铁人王进喜纪念馆");
//        mid2Name.put("mMarket_37","瑷珲历史陈列馆");
//        mid2Name.put("mMarket_38","黑龙江省博物馆");
//        mid2Name.put("mMarket_39","大庆博物馆");
//        mid2Name.put("mMarket_40","上海博物馆");
//        mid2Name.put("mMarket_41","上海鲁迅纪念馆");
//        mid2Name.put("mMarket_42","中共一大会址纪念馆");
//        mid2Name.put("mMarket_43","上海科技馆");
//        mid2Name.put("mMarket_44","陈云纪念馆");
//        mid2Name.put("mMarket_45","南京博物院");
//        mid2Name.put("mMarket_46","侵华日军南京大屠杀遇难同胞纪念馆");
//        mid2Name.put("mMarket_47","南通博物苑");
//        mid2Name.put("mMarket_48","苏州博物馆");
//        mid2Name.put("mMarket_49","扬州博物馆");
//        mid2Name.put("mMarket_50","常州博物馆");
//        mid2Name.put("mMarket_51","南京市博物总馆");
//        mid2Name.put("mMarket_52","浙江省博物馆");
//        mid2Name.put("mMarket_53","浙江自然博物馆");
//        mid2Name.put("mMarket_54","中国丝绸博物馆");
//        mid2Name.put("mMarket_55","宁波博物馆");
//        mid2Name.put("mMarket_56","杭州博物馆");
//        mid2Name.put("mMarket_57","温州博物馆");
//        mid2Name.put("mMarket_58","安徽省博物馆");
//        mid2Name.put("mMarket_59","安徽中国徽州文化博物馆");
//        mid2Name.put("mMarket_60","福建博物院");
//        mid2Name.put("mMarket_61","古田会议纪念馆");
//        mid2Name.put("mMarket_62","泉州海外交通史博物馆");
//        mid2Name.put("mMarket_63","华侨博物院");
//        mid2Name.put("mMarket_64","中国闽台缘博物馆");
//        mid2Name.put("mMarket_65","中央苏区（闽西）历史博物馆");
//        mid2Name.put("mMarket_66","井冈山革命博物馆");
//        mid2Name.put("mMarket_67","江西省博物馆");
//        mid2Name.put("mMarket_68","瑞金中央革命根据地纪念馆");
//        mid2Name.put("mMarket_69","南昌八一起义纪念馆");
//        mid2Name.put("mMarket_70","安源路矿工人运动纪念馆");
//        mid2Name.put("mMarket_71","中国海军博物馆");
//        mid2Name.put("mMarket_72","青岛市博物馆");
//        mid2Name.put("mMarket_73","中国甲午战争博物馆");
//        mid2Name.put("mMarket_74","青州博物馆");
//        mid2Name.put("mMarket_75","山东博物馆");
//        mid2Name.put("mMarket_76","烟台市博物馆");
//        mid2Name.put("mMarket_77","潍坊市博物馆");
//        mid2Name.put("mMarket_78","河南博物院");
//        mid2Name.put("mMarket_79","郑州博物馆");
//        mid2Name.put("mMarket_80","洛阳博物馆");
//        mid2Name.put("mMarket_81","南阳汉画馆");
//        mid2Name.put("mMarket_82","开封市博物馆");
//        mid2Name.put("mMarket_83","鄂豫皖苏区首府革命博物馆");
//        mid2Name.put("mMarket_84","湖北省博物馆");
//        mid2Name.put("mMarket_85","荆州博物馆");
//        mid2Name.put("mMarket_86","武汉博物馆");
//        mid2Name.put("mMarket_87","辛亥革命武昌起义纪念馆");
//        mid2Name.put("mMarket_88","武汉市中山舰博物馆");
//        mid2Name.put("mMarket_89","湖南省博物馆");
//        mid2Name.put("mMarket_90","韶山毛泽东故居纪念馆");
//        mid2Name.put("mMarket_91","刘少奇故居纪念馆");
//        mid2Name.put("mMarket_92","长沙简牍博物馆");
//        mid2Name.put("mMarket_93","广东省博物馆");
//        mid2Name.put("mMarket_94","西汉南越王博物馆");
//        mid2Name.put("mMarket_95","孙中山故居纪念馆");
//        mid2Name.put("mMarket_96","深圳博物馆");
//        mid2Name.put("mMarket_97","广州博物馆");
//        mid2Name.put("mMarket_98","广东民间工艺博物馆");
//        mid2Name.put("mMarket_99","广西壮族自治区博物馆");
//        mid2Name.put("mMarket_100","广西民族博物馆");
//        mid2Name.put("mMarket_101","海南省博物馆");
//        mid2Name.put("mMarket_102","自贡恐龙博物馆");
//        mid2Name.put("mMarket_103","三星堆博物馆");
//        mid2Name.put("mMarket_104","成都武侯祠博物馆");
//        mid2Name.put("mMarket_105","邓小平故居陈列馆");
//        mid2Name.put("mMarket_106","成都杜甫草堂博物馆");
//        mid2Name.put("mMarket_107","四川博物院");
//        mid2Name.put("mMarket_108","成都金沙遗址博物馆");
//        mid2Name.put("mMarket_109","自贡市盐业历史博物馆");
//        mid2Name.put("mMarket_110","遵义会议纪念馆");
//        mid2Name.put("mMarket_111","云南省博物馆");
//        mid2Name.put("mMarket_112","云南民族博物馆");
//        mid2Name.put("mMarket_113","重庆中国三峡博物馆");
//        mid2Name.put("mMarket_114","重庆红岩革命历史博物馆");
//        mid2Name.put("mMarket_115","重庆自然博物馆");
//        mid2Name.put("mMarket_116","西藏博物馆");
//        mid2Name.put("mMarket_117","陕西历史博物馆");
//        mid2Name.put("mMarket_118","秦始皇兵马俑博物馆");
//        mid2Name.put("mMarket_119","延安革命纪念馆");
//        mid2Name.put("mMarket_120","汉阳陵博物馆");
//        mid2Name.put("mMarket_121","西安碑林博物馆");
//        mid2Name.put("mMarket_122","西安半坡博物馆");
//        mid2Name.put("mMarket_123","西安博物院");
//        mid2Name.put("mMarket_124","宝鸡青铜器博物院");
//        mid2Name.put("mMarket_125","西安大唐西市博物馆");
//        mid2Name.put("mMarket_126","甘肃省博物馆");
//        mid2Name.put("mMarket_127","天水市博物馆");
//        mid2Name.put("mMarket_128","敦煌研究院");
//        mid2Name.put("mMarket_129","固原博物馆");
//        mid2Name.put("mMarket_130","宁夏博物馆");
//        mid2Name.put("mMarket_131","青海省博物馆");
//        mid2Name.put("mMarket_132","新疆维吾尔自治区博物馆");
//        mid2Name.put("mMarket_133","吐鲁番博物馆");
//
//        latLngHashMap.put("mMarket_1",new LatLng(39.924091,116.403414));
//        latLngHashMap.put("mMarket_2",new LatLng(40.012384,116.40504));
//        latLngHashMap.put("mMarket_3",new LatLng(39.929518,116.378653));
//        latLngHashMap.put("mMarket_4",new LatLng(39.914976,116.330207));
//        latLngHashMap.put("mMarket_5",new LatLng(40.192095,116.372885));
//        latLngHashMap.put("mMarket_6",new LatLng(39.931656,116.365314));
//        latLngHashMap.put("mMarket_7",new LatLng(39.912174,116.348822));
//        latLngHashMap.put("mMarket_8",new LatLng(39.889525,116.406116));
//        latLngHashMap.put("mMarket_9",new LatLng(39.857753,116.232474));
//        latLngHashMap.put("mMarket_10",new LatLng(39.943273,116.343208));
//        latLngHashMap.put("mMarket_11",new LatLng(39.693589,115.943241));
//        latLngHashMap.put("mMarket_12",new LatLng(39.91146,116.408016));
//        latLngHashMap.put("mMarket_13",new LatLng(39.947016,116.47137));
//        latLngHashMap.put("mMarket_14",new LatLng(39.942181,116.392805));
//        latLngHashMap.put("mMarket_15",new LatLng(39.091039,117.218282));
//        latLngHashMap.put("mMarket_16",new LatLng(39.091766,117.215291));
//        latLngHashMap.put("mMarket_17",new LatLng(39.100099,117.17134));
//        latLngHashMap.put("mMarket_18",new LatLng(38.046443,114.529277));
//        latLngHashMap.put("mMarket_19",new LatLng(38.345586,113.95144));
//        latLngHashMap.put("mMarket_20",new LatLng(36.616094,114.529465));
//        latLngHashMap.put("mMarket_21",new LatLng(37.871448,112.537791));
//        latLngHashMap.put("mMarket_22",new LatLng(37.863006,112.534993));
//        latLngHashMap.put("mMarket_23",new LatLng(36.839016,112.862735));
//        latLngHashMap.put("mMarket_24",new LatLng(40.845845,111.745156));
//        latLngHashMap.put("mMarket_25",new LatLng(39.607385,109.791447));
//        latLngHashMap.put("mMarket_26",new LatLng(41.684248,123.466951));
//        latLngHashMap.put("mMarket_27",new LatLng(41.842745,123.473965));
//        latLngHashMap.put("mMarket_28",new LatLng(41.861163,123.456283));
//        latLngHashMap.put("mMarket_29",new LatLng(38.814166,121.241609));
//        latLngHashMap.put("mMarket_30",new LatLng(41.803282,123.46221));
//        latLngHashMap.put("mMarket_31",new LatLng(38.894559,121.592569));
//        latLngHashMap.put("mMarket_32",new LatLng(43.835539,125.43943));
//        latLngHashMap.put("mMarket_33",new LatLng(43.774571,125.439227));
//        latLngHashMap.put("mMarket_34",new LatLng(43.91146,125.355281));
//        latLngHashMap.put("mMarket_35",new LatLng(45.771657,126.64905));
//        latLngHashMap.put("mMarket_36",new LatLng(46.615292,124.893237));
//        latLngHashMap.put("mMarket_37",new LatLng(49.98463,127.499443));
//        latLngHashMap.put("mMarket_38",new LatLng(45.763371,126.647534));
//        latLngHashMap.put("mMarket_39",new LatLng(46.590133,125.160197));
//        latLngHashMap.put("mMarket_40",new LatLng(31.234143,121.48223));
//        latLngHashMap.put("mMarket_41",new LatLng(31.275505,121.490442));
//        latLngHashMap.put("mMarket_42",new LatLng(31.226086,121.481667));
//        latLngHashMap.put("mMarket_43",new LatLng(31.224356,121.547823));
//        latLngHashMap.put("mMarket_44",new LatLng(31.012765,121.050436));
//        latLngHashMap.put("mMarket_45",new LatLng(32.046772,118.83165));
//        latLngHashMap.put("mMarket_46",new LatLng(32.041149,118.749884));
//        latLngHashMap.put("mMarket_47",new LatLng(32.017272,120.876309));
//        latLngHashMap.put("mMarket_48",new LatLng(31.329059,120.634317));
//        latLngHashMap.put("mMarket_49",new LatLng(32.397431,119.378544));
//        latLngHashMap.put("mMarket_50",new LatLng(31.814896,119.978141));
//        latLngHashMap.put("mMarket_51",new LatLng(32.040078,118.782203));
//        latLngHashMap.put("mMarket_52",new LatLng(30.257342,120.149943));
//        latLngHashMap.put("mMarket_53",new LatLng(30.282674,120.171317));
//        latLngHashMap.put("mMarket_54",new LatLng(30.228906,120.157949));
//        latLngHashMap.put("mMarket_55",new LatLng(29.821188,121.551803));
//        latLngHashMap.put("mMarket_56",new LatLng(30.244426,120.173093));
//        latLngHashMap.put("mMarket_57",new LatLng(27.994791,120.704546));
//        latLngHashMap.put("mMarket_58",new LatLng(31.808263,117.226663));
//        latLngHashMap.put("mMarket_59",new LatLng(29.7234,118.281156));
//        latLngHashMap.put("mMarket_60",new LatLng(26.100197,119.293236));
//        latLngHashMap.put("mMarket_61",new LatLng(25.227348,116.829662));
//        latLngHashMap.put("mMarket_62",new LatLng(24.915806,118.617926));
//        latLngHashMap.put("mMarket_63",new LatLng(24.448392,118.096458));
//        latLngHashMap.put("mMarket_64",new LatLng(24.941018,118.596435));
//        latLngHashMap.put("mMarket_65",new LatLng(25.108544,117.030617));
//        latLngHashMap.put("mMarket_66",new LatLng(26.573406,114.172166));
//        latLngHashMap.put("mMarket_67",new LatLng(28.711549,115.888431));
//        latLngHashMap.put("mMarket_68",new LatLng(25.88044,116.021135));
//        latLngHashMap.put("mMarket_69",new LatLng(28.680744,115.895908));
//        latLngHashMap.put("mMarket_70",new LatLng(27.609583,113.903491));
//        latLngHashMap.put("mMarket_71",new LatLng(36.061096,120.337085));
//        latLngHashMap.put("mMarket_72",new LatLng(36.107914,120.479533));
//        latLngHashMap.put("mMarket_73",new LatLng(37.505406,122.193762));
//        latLngHashMap.put("mMarket_74",new LatLng(36.690088,118.469183));
//        latLngHashMap.put("mMarket_75",new LatLng(36.664652,117.102287));
//        latLngHashMap.put("mMarket_76",new LatLng(37.542843,121.39963));
//        latLngHashMap.put("mMarket_77",new LatLng(36.718352,119.165131));
//        latLngHashMap.put("mMarket_78",new LatLng(34.794165,113.678452));
//        latLngHashMap.put("mMarket_79",new LatLng(34.751534,113.63384));
//        latLngHashMap.put("mMarket_80",new LatLng(34.64963,112.457992));
//        latLngHashMap.put("mMarket_81",new LatLng(32.976556,112.512775));
//        latLngHashMap.put("mMarket_82",new LatLng(34.809781,114.260221));
//        latLngHashMap.put("mMarket_83",new LatLng(31.622785,114.886061));
//        latLngHashMap.put("mMarket_84",new LatLng(30.567828,114.371879));
//        latLngHashMap.put("mMarket_85",new LatLng(30.359302,112.186803));
//        latLngHashMap.put("mMarket_86",new LatLng(30.617735,114.263081));
//        latLngHashMap.put("mMarket_87",new LatLng(30.547866,114.312911));
//        latLngHashMap.put("mMarket_88",new LatLng(30.351522,114.139869));
//        latLngHashMap.put("mMarket_89",new LatLng(28.217827,113.000003));
//        latLngHashMap.put("mMarket_90",new LatLng(27.909605,112.500642));
//        latLngHashMap.put("mMarket_91",new LatLng(28.041642,112.653727));
//        latLngHashMap.put("mMarket_92",new LatLng(28.190617,112.989772));
//        latLngHashMap.put("mMarket_93",new LatLng(23.120486,113.332975));
//        latLngHashMap.put("mMarket_94",new LatLng(23.144033,113.267474));
//        latLngHashMap.put("mMarket_95",new LatLng(22.449309,113.534598));
//        latLngHashMap.put("mMarket_96",new LatLng(22.54928,114.06827));
//        latLngHashMap.put("mMarket_97",new LatLng(23.144241,113.272097));
//        latLngHashMap.put("mMarket_98",new LatLng(23.133185,113.252021));
//        latLngHashMap.put("mMarket_99",new LatLng(22.818472,108.341755));
//        latLngHashMap.put("mMarket_100",new LatLng(22.780319,108.408402));
//        latLngHashMap.put("mMarket_101",new LatLng(20.021773,110.385478));
//        latLngHashMap.put("mMarket_102",new LatLng(29.401369,104.836234));
//        latLngHashMap.put("mMarket_103",new LatLng(31.007167,104.225198));
//        latLngHashMap.put("mMarket_104",new LatLng(30.652582,104.054572));
//        latLngHashMap.put("mMarket_105",new LatLng(30.522298,106.640775));
//        latLngHashMap.put("mMarket_106",new LatLng(30.666397,104.034848));
//        latLngHashMap.put("mMarket_107",new LatLng(30.667087,104.040528));
//        latLngHashMap.put("mMarket_108",new LatLng(30.687743,104.019116));
//        latLngHashMap.put("mMarket_109",new LatLng(29.355016,104.777752));
//        latLngHashMap.put("mMarket_110",new LatLng(27.694643,106.927126));
//        latLngHashMap.put("mMarket_111",new LatLng(24.955361,102.760017));
//        latLngHashMap.put("mMarket_112",new LatLng(24.970827,102.675693));
//        latLngHashMap.put("mMarket_113",new LatLng(29.568347,106.556883));
//        latLngHashMap.put("mMarket_114",new LatLng(29.578462,106.448726));
//        latLngHashMap.put("mMarket_115",new LatLng(29.82499,106.414221));
//        latLngHashMap.put("mMarket_116",new LatLng(29.654906,91.104815));
//        latLngHashMap.put("mMarket_117",new LatLng(34.230523,108.961495));
//        latLngHashMap.put("mMarket_118",new LatLng(34.389417,109.2851));
//        latLngHashMap.put("mMarket_119",new LatLng(36.614936,109.484034));
//        latLngHashMap.put("mMarket_120",new LatLng(34.447437,108.959324));
//        latLngHashMap.put("mMarket_121",new LatLng(34.26019,108.959139));
//        latLngHashMap.put("mMarket_122",new LatLng(34.279207,109.05967));
//        latLngHashMap.put("mMarket_123",new LatLng(34.243512,108.947004));
//        latLngHashMap.put("mMarket_124",new LatLng(34.353483,107.201749));
//        latLngHashMap.put("mMarket_125",new LatLng(34.253175,108.91452));
//        latLngHashMap.put("mMarket_126",new LatLng(36.072733,103.781754));
//        latLngHashMap.put("mMarket_127",new LatLng(34.586439,105.713197));
//        latLngHashMap.put("mMarket_128",new LatLng(36.068208,103.85432));
//        latLngHashMap.put("mMarket_129",new LatLng(36.011249,106.282598));
//        latLngHashMap.put("mMarket_130",new LatLng(38.490741,106.241638));
//        latLngHashMap.put("mMarket_131",new LatLng(36.636538,101.762535));
//        latLngHashMap.put("mMarket_132",new LatLng(43.825756,87.590787));
//        latLngHashMap.put("mMarket_133",new LatLng(42.948874,89.197715));
//    }


}
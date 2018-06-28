package com.avicsafety.safety_examine.model;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class AddressBean {


    /**
     * status : 0
     * result : {"location":{"lng":123.49583399999993,"lat":41.81995088709948},"formatted_address":"辽宁省沈阳市大东区珠林路238-4号","business":"珠林,东塔,大东路","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"辽宁省","city":"沈阳市","city_level":2,"district":"大东区","town":"","adcode":"210104","street":"珠林路","street_number":"238-4号","direction":"西","distance":"64"},"pois":[{"addr":"沈阳市大东区锦园路26号","cp":"","direction":"内","distance":"0","name":"锦绣花园","poiType":"房地产","point":{"x":123.496319626254,"y":41.820002897968266},"tag":"房地产;住宅区","tel":"","uid":"07f3c8b520aeb4636340a9a3","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"辽宁省沈阳市大东区锦园路22","cp":" ","direction":"附近","distance":"14","name":"小骨头羊杂馆","poiType":"美食","point":{"x":123.49570877850722,"y":41.81998273826795},"tag":"美食;中餐厅","tel":"","uid":"2dfde528c81ea850bda240cd","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路锦绣花园10号","cp":" ","direction":"附近","distance":"18","name":"三星烧烤火锅店","poiType":"美食","point":{"x":123.49583454127861,"y":41.8200768168147},"tag":"美食;中餐厅","tel":"","uid":"73a094e06a198bbbed74f8b2","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路238-4号","cp":" ","direction":"附近","distance":"24","name":"锦绣花园-14号楼","poiType":"内部楼号","point":{"x":123.49597827016021,"y":41.81982818035315},"tag":"内部楼号","tel":"","uid":"2962bdae141254af9868b43f","zip":"","parent_poi":{"name":"锦绣花园","tag":"房地产;住宅区","addr":"沈阳市大东区锦园路26号","point":{"x":123.49638250763971,"y":41.82002977755874},"direction":"西","distance":"62","uid":"07f3c8b520aeb4636340a9a3"}},{"addr":"珠林路240号锦绣花园内","cp":" ","direction":"附近","distance":"22","name":"十里香巧菜馆","poiType":"美食","point":{"x":123.49562793101133,"y":41.81994913875324},"tag":"美食;中餐厅","tel":"","uid":"8803ccc94a1fd5e6aeb22a83","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路238-4号","cp":" ","direction":"附近","distance":"39","name":"聚鑫源川菜馆","poiType":"美食","point":{"x":123.49585250738882,"y":41.81968706192877},"tag":"美食;中餐厅","tel":"","uid":"a2f37d9d5a40ba93f7b916ff","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"北海街道锦绣花园18号楼","cp":" ","direction":"附近","distance":"47","name":"金山烧烤狗肉馆","poiType":"美食","point":{"x":123.49609504987652,"y":41.82020449461967},"tag":"美食;中餐厅","tel":"","uid":"11e5396738a14476b5f42fdd","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路24号附近","cp":" ","direction":"西南","distance":"53","name":"制作牌匾","poiType":"购物","point":{"x":123.49613996515201,"y":41.82023137412488},"tag":"购物;商铺","tel":"","uid":"fbb24755d4d7a25103ec01ee","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"沈阳市大东区珠林路238-4","cp":" ","direction":"东","distance":"95","name":"锦园浴池","poiType":"休闲娱乐","point":{"x":123.49498115104414,"y":41.81988865958196},"tag":"休闲娱乐;洗浴按摩","tel":"","uid":"33ff9eb39f42ecba8936ee8e","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"锦园路24号","cp":" ","direction":"西南","distance":"113","name":"沈阳秀香源食品有限责任公司","poiType":"公司企业","point":{"x":123.4964453890254,"y":41.82056064714111},"tag":"公司企业;公司","tel":"","uid":"698c3b68d0ab591a40e1921d","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}}],"roads":[],"poiRegions":[{"direction_desc":"内","name":"锦绣花园","tag":"房地产;住宅区","uid":"07f3c8b520aeb4636340a9a3"}],"sematic_description":"锦绣花园内,小骨头羊杂馆附近14米","cityCode":58}
     */

    private int status;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"lng":123.49583399999993,"lat":41.81995088709948}
         * formatted_address : 辽宁省沈阳市大东区珠林路238-4号
         * business : 珠林,东塔,大东路
         * addressComponent : {"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"辽宁省","city":"沈阳市","city_level":2,"district":"大东区","town":"","adcode":"210104","street":"珠林路","street_number":"238-4号","direction":"西","distance":"64"}
         * pois : [{"addr":"沈阳市大东区锦园路26号","cp":"","direction":"内","distance":"0","name":"锦绣花园","poiType":"房地产","point":{"x":123.496319626254,"y":41.820002897968266},"tag":"房地产;住宅区","tel":"","uid":"07f3c8b520aeb4636340a9a3","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"辽宁省沈阳市大东区锦园路22","cp":" ","direction":"附近","distance":"14","name":"小骨头羊杂馆","poiType":"美食","point":{"x":123.49570877850722,"y":41.81998273826795},"tag":"美食;中餐厅","tel":"","uid":"2dfde528c81ea850bda240cd","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路锦绣花园10号","cp":" ","direction":"附近","distance":"18","name":"三星烧烤火锅店","poiType":"美食","point":{"x":123.49583454127861,"y":41.8200768168147},"tag":"美食;中餐厅","tel":"","uid":"73a094e06a198bbbed74f8b2","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路238-4号","cp":" ","direction":"附近","distance":"24","name":"锦绣花园-14号楼","poiType":"内部楼号","point":{"x":123.49597827016021,"y":41.81982818035315},"tag":"内部楼号","tel":"","uid":"2962bdae141254af9868b43f","zip":"","parent_poi":{"name":"锦绣花园","tag":"房地产;住宅区","addr":"沈阳市大东区锦园路26号","point":{"x":123.49638250763971,"y":41.82002977755874},"direction":"西","distance":"62","uid":"07f3c8b520aeb4636340a9a3"}},{"addr":"珠林路240号锦绣花园内","cp":" ","direction":"附近","distance":"22","name":"十里香巧菜馆","poiType":"美食","point":{"x":123.49562793101133,"y":41.81994913875324},"tag":"美食;中餐厅","tel":"","uid":"8803ccc94a1fd5e6aeb22a83","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路238-4号","cp":" ","direction":"附近","distance":"39","name":"聚鑫源川菜馆","poiType":"美食","point":{"x":123.49585250738882,"y":41.81968706192877},"tag":"美食;中餐厅","tel":"","uid":"a2f37d9d5a40ba93f7b916ff","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"北海街道锦绣花园18号楼","cp":" ","direction":"附近","distance":"47","name":"金山烧烤狗肉馆","poiType":"美食","point":{"x":123.49609504987652,"y":41.82020449461967},"tag":"美食;中餐厅","tel":"","uid":"11e5396738a14476b5f42fdd","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"珠林路24号附近","cp":" ","direction":"西南","distance":"53","name":"制作牌匾","poiType":"购物","point":{"x":123.49613996515201,"y":41.82023137412488},"tag":"购物;商铺","tel":"","uid":"fbb24755d4d7a25103ec01ee","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"沈阳市大东区珠林路238-4","cp":" ","direction":"东","distance":"95","name":"锦园浴池","poiType":"休闲娱乐","point":{"x":123.49498115104414,"y":41.81988865958196},"tag":"休闲娱乐;洗浴按摩","tel":"","uid":"33ff9eb39f42ecba8936ee8e","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"锦园路24号","cp":" ","direction":"西南","distance":"113","name":"沈阳秀香源食品有限责任公司","poiType":"公司企业","point":{"x":123.4964453890254,"y":41.82056064714111},"tag":"公司企业;公司","tel":"","uid":"698c3b68d0ab591a40e1921d","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}}]
         * roads : []
         * poiRegions : [{"direction_desc":"内","name":"锦绣花园","tag":"房地产;住宅区","uid":"07f3c8b520aeb4636340a9a3"}]
         * sematic_description : 锦绣花园内,小骨头羊杂馆附近14米
         * cityCode : 58
         */

        private LocationBean location;
        private String formatted_address;
        private String business;
        private AddressComponentBean addressComponent;
        private String sematic_description;
        private int cityCode;
        private List<PoisBean> pois;
        private List<?> roads;
        private List<PoiRegionsBean> poiRegions;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public AddressComponentBean getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentBean addressComponent) {
            this.addressComponent = addressComponent;
        }

        public String getSematic_description() {
            return sematic_description;
        }

        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public List<PoisBean> getPois() {
            return pois;
        }

        public void setPois(List<PoisBean> pois) {
            this.pois = pois;
        }

        public List<?> getRoads() {
            return roads;
        }

        public void setRoads(List<?> roads) {
            this.roads = roads;
        }

        public List<PoiRegionsBean> getPoiRegions() {
            return poiRegions;
        }

        public void setPoiRegions(List<PoiRegionsBean> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public static class LocationBean {
            /**
             * lng : 123.49583399999993
             * lat : 41.81995088709948
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }

        public static class AddressComponentBean {
            /**
             * country : 中国
             * country_code : 0
             * country_code_iso : CHN
             * country_code_iso2 : CN
             * province : 辽宁省
             * city : 沈阳市
             * city_level : 2
             * district : 大东区
             * town :
             * adcode : 210104
             * street : 珠林路
             * street_number : 238-4号
             * direction : 西
             * distance : 64
             */

            private String country;
            private int country_code;
            private String country_code_iso;
            private String country_code_iso2;
            private String province;
            private String city;
            private int city_level;
            private String district;
            private String town;
            private String adcode;
            private String street;
            private String street_number;
            private String direction;
            private String distance;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getCountry_code() {
                return country_code;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public String getCountry_code_iso() {
                return country_code_iso;
            }

            public void setCountry_code_iso(String country_code_iso) {
                this.country_code_iso = country_code_iso;
            }

            public String getCountry_code_iso2() {
                return country_code_iso2;
            }

            public void setCountry_code_iso2(String country_code_iso2) {
                this.country_code_iso2 = country_code_iso2;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCity_level() {
                return city_level;
            }

            public void setCity_level(int city_level) {
                this.city_level = city_level;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class PoisBean {
            /**
             * addr : 沈阳市大东区锦园路26号
             * cp :
             * direction : 内
             * distance : 0
             * name : 锦绣花园
             * poiType : 房地产
             * point : {"x":123.496319626254,"y":41.820002897968266}
             * tag : 房地产;住宅区
             * tel :
             * uid : 07f3c8b520aeb4636340a9a3
             * zip :
             * parent_poi : {"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}
             */

            private String addr;
            private String cp;
            private String direction;
            private String distance;
            private String name;
            private String poiType;
            private PointBean point;
            private String tag;
            private String tel;
            private String uid;
            private String zip;
            private ParentPoiBean parent_poi;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPoiType() {
                return poiType;
            }

            public void setPoiType(String poiType) {
                this.poiType = poiType;
            }

            public PointBean getPoint() {
                return point;
            }

            public void setPoint(PointBean point) {
                this.point = point;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public ParentPoiBean getParent_poi() {
                return parent_poi;
            }

            public void setParent_poi(ParentPoiBean parent_poi) {
                this.parent_poi = parent_poi;
            }

            public static class PointBean {
                /**
                 * x : 123.496319626254
                 * y : 41.820002897968266
                 */

                private double x;
                private double y;

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }
            }

            public static class ParentPoiBean {
                /**
                 * name :
                 * tag :
                 * addr :
                 * point : {"x":0,"y":0}
                 * direction :
                 * distance :
                 * uid :
                 */

                private String name;
                private String tag;
                private String addr;
                private PointBeanX point;
                private String direction;
                private String distance;
                private String uid;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public PointBeanX getPoint() {
                    return point;
                }

                public void setPoint(PointBeanX point) {
                    this.point = point;
                }

                public String getDirection() {
                    return direction;
                }

                public void setDirection(String direction) {
                    this.direction = direction;
                }

                public String getDistance() {
                    return distance;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public static class PointBeanX {
                    /**
                     * x : 0.0
                     * y : 0.0
                     */

                    private double x;
                    private double y;

                    public double getX() {
                        return x;
                    }

                    public void setX(double x) {
                        this.x = x;
                    }

                    public double getY() {
                        return y;
                    }

                    public void setY(double y) {
                        this.y = y;
                    }
                }
            }
        }

        public static class PoiRegionsBean {
            /**
             * direction_desc : 内
             * name : 锦绣花园
             * tag : 房地产;住宅区
             * uid : 07f3c8b520aeb4636340a9a3
             */

            private String direction_desc;
            private String name;
            private String tag;
            private String uid;

            public String getDirection_desc() {
                return direction_desc;
            }

            public void setDirection_desc(String direction_desc) {
                this.direction_desc = direction_desc;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }
    }
}

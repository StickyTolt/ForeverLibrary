package com.martin.alllibrary.activity.model;

import java.util.List;

/**
 * 作者：Martin on 2017/8/23 14:21
 * 邮箱：martin0207mfh@163.com
 */
public class AreaModel {


    private List<ProvinceBean> province;

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public static class ProvinceBean {
        /**
         * city : [{"district":[{"name":"昌平区","zipcode":"100000","isChecked":true},{"name":"朝阳区","zipcode":"100000"},{"name":"崇文区","zipcode":"100000"},{"name":"大兴区","zipcode":"100000"},{"name":"东城区","zipcode":"100000"},{"name":"房山区","zipcode":"100000"},{"name":"丰台区","zipcode":"100000"},{"name":"海淀区","zipcode":"100000"},{"name":"怀柔区","zipcode":"100000"},{"name":"门头沟区","zipcode":"100000"},{"name":"密云县","zipcode":"100000"},{"name":"平谷区","zipcode":"100000"},{"name":"石景山区","zipcode":"100000"},{"name":"顺义区","zipcode":"100000"},{"name":"通州区","zipcode":"100000"},{"name":"西城区","zipcode":"100000"},{"name":"宣武区","zipcode":"100000"},{"name":"延庆县","zipcode":"100000"},{"name":"其他","zipcode":"100000"}],"name":"北京市","isChecked":true}]
         * name : 北京市
         * isChecked : true
         */

        private String name;
        private boolean isChecked = false;
        private List<CityBean> city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * district : [{"name":"昌平区","zipcode":"100000","isChecked":true},{"name":"朝阳区","zipcode":"100000"},{"name":"崇文区","zipcode":"100000"},{"name":"大兴区","zipcode":"100000"},{"name":"东城区","zipcode":"100000"},{"name":"房山区","zipcode":"100000"},{"name":"丰台区","zipcode":"100000"},{"name":"海淀区","zipcode":"100000"},{"name":"怀柔区","zipcode":"100000"},{"name":"门头沟区","zipcode":"100000"},{"name":"密云县","zipcode":"100000"},{"name":"平谷区","zipcode":"100000"},{"name":"石景山区","zipcode":"100000"},{"name":"顺义区","zipcode":"100000"},{"name":"通州区","zipcode":"100000"},{"name":"西城区","zipcode":"100000"},{"name":"宣武区","zipcode":"100000"},{"name":"延庆县","zipcode":"100000"},{"name":"其他","zipcode":"100000"}]
             * name : 北京市
             * isChecked : true
             */

            private String name;
            private boolean isChecked = false;
            private List<DistrictBean> district;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isIsChecked() {
                return isChecked;
            }

            public void setIsChecked(boolean isChecked) {
                this.isChecked = isChecked;
            }

            public List<DistrictBean> getDistrict() {
                return district;
            }

            public void setDistrict(List<DistrictBean> district) {
                this.district = district;
            }

            public static class DistrictBean {
                /**
                 * name : 昌平区
                 * zipcode : 100000
                 * isChecked : true
                 */

                private String name;
                private String zipcode;
                private boolean isChecked = false;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getZipcode() {
                    return zipcode;
                }

                public void setZipcode(String zipcode) {
                    this.zipcode = zipcode;
                }

                public boolean isIsChecked() {
                    return isChecked;
                }

                public void setIsChecked(boolean isChecked) {
                    this.isChecked = isChecked;
                }
            }
        }
    }
}

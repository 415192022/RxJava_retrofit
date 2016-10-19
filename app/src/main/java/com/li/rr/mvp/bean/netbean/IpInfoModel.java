package com.li.rr.mvp.bean.netbean;

/**
 * Created by Administrator on 2016/6/7.
 */
public class IpInfoModel {
    String code;
    Data data;

    @Override
    public String toString() {
        return "IpInfoModel{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public class Data{
        String country;
        String country_id;
        String area;
        String area_id;
        String region;
        String region_id;
        String city;
        String city_id;
        String county;
        String county_id;
        String isp;
        String isp_id;
        String ip;

        public void setCountry(String country) {
            this.country = country;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public void setCounty_id(String county_id) {
            this.county_id = county_id;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public void setIsp_id(String isp_id) {
            this.isp_id = isp_id;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCountry() {
            return country;
        }

        public String getCountry_id() {
            return country_id;
        }

        public String getArea() {
            return area;
        }

        public String getArea_id() {
            return area_id;
        }

        public String getRegion() {
            return region;
        }

        public String getRegion_id() {
            return region_id;
        }

        public String getCity() {
            return city;
        }

        public String getCity_id() {
            return city_id;
        }

        public String getCounty() {
            return county;
        }

        public String getCounty_id() {
            return county_id;
        }

        public String getIsp() {
            return isp;
        }

        public String getIsp_id() {
            return isp_id;
        }

        public String getIp() {
            return ip;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "country='" + country + '\'' +
                    ", country_id='" + country_id + '\'' +
                    ", area='" + area + '\'' +
                    ", area_id='" + area_id + '\'' +
                    ", region='" + region + '\'' +
                    ", region_id='" + region_id + '\'' +
                    ", city='" + city + '\'' +
                    ", city_id='" + city_id + '\'' +
                    ", county='" + county + '\'' +
                    ", county_id='" + county_id + '\'' +
                    ", isp='" + isp + '\'' +
                    ", isp_id='" + isp_id + '\'' +
                    ", ip='" + ip + '\'' +
                    '}';
        }
    }
}

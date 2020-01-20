package com.example.weathercity.model;

import java.util.List;

public class ModelForecast {

    private java.util.List<List> list;

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> value) {
        this.list = value;
    }

    public class List {
        private MainClass main;
        private java.util.List<Weather> weather;
        private String dt_txt;

        public MainClass getMain() {
            return main;
        }

        public java.util.List<Weather> getWeather() {
            return weather;
        }

        public String getDtTxt() {
            return dt_txt;
        }

        public class MainClass {
            private double temp;
            private double temp_min;
            private double temp_max;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double value) {
                this.temp = value;
            }

            public double getTempMin() {
                return temp_min;
            }

            public void setTempMin(double value) {
                this.temp_min = value;
            }

            public double getTempMax() {
                return temp_max;
            }

            public void setTempMax(double value) {
                this.temp_max = value;
            }
        }

        public class Weather {
            private String icon;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String value) {
                this.icon = value;
            }
        }
    }


}

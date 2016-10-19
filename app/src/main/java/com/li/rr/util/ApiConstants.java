/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.li.rr.util;

import android.os.Environment;

/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/3/9.
 * Description:
 */
public class ApiConstants {

    public static final class Urls {
        public static final String BAIDU_IMAGES_URLS = "http://image.baidu.com/data/imgs";
        public static final String YOUKU_VIDEOS_URLS = "https://openapi.youku.com/v2/searches/video/by_keyword.json";
        public static final String YOUKU_USER_URLS = "https://openapi.youku.com/v2/users/show.json";
        public static final String DOUBAN_PLAY_LIST_URLS = "http://www.douban.com/j/app/radio/people";
        public static final String DOUBAN_BASEURL = "https://api.douban.com/v2/movie/";
        public static final String DOUBAN_IMAGE = "https://img3.doubanio.com/img/";
        public static final String IP_INFO_BASE_URLS="http://ip.taobao.com/service/";
    }

    public static final class Suffix{
        public static final String SUFFIX_TXT="txt";
        public static final String SUFFIX_PDF="pdf";
        public static final String SUFFIX_XML="xml";
        public static final String SUFFIX_JPG="jpg";
        public static final String SUFFIX_PNG="png";
        public static final String SUFFIX_OOG="oog";
        public static final String SUFFIX_HTM="htm";
        public static final String SUFFIX_HTML="html";
        public static final String SUFFIX_PROP="prop";
    }
    public static final class Paths {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        public static final String IMAGE_LOADER_CACHE_PATH = "/SimplifyReader/Images/";
    }

    public static final class Integers {
        public static final int PAGE_LAZY_LOAD_DELAY_TIME_MS = 200;
    }
}
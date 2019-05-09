package com.hlw.webmagic.begin.MyComic;

import com.hlw.webmagic.begin.beginThree.helper.DownUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-08 23:33
 **/
public class MyComicPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        String imgUrl = resultItems.get("imgUrl");
        if (imgUrl != null) {
            String imgName = StringUtils.substringAfterLast(imgUrl, "/");
            String path = "D:\\共享电影\\漫画\\邪恶漫画";
            String imgPath = path + "\\" + imgName;
            boolean b = DownUtils.downloadPicture(imgUrl, imgPath);
        }
    }
}



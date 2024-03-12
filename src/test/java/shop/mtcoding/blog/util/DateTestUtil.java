package shop.mtcoding.blog.util;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class DateTestUtil {

    @Test
    public void timestampFormat_test(){
        // given
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // when
        String createdAt = MyDateUtil.timestampFormat(currentTimestamp);

        // then
        System.out.println("timestampFormat_test : " + createdAt);
    }

}

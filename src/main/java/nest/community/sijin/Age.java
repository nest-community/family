package nest.community.sijin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 * 计算右右的准确年龄，精确到分，包括满多少天第多少天
 *
 * @author SAGITTAR
 */
public class Age {

    public static void main(String[] args) {
        if (Objects.isNull(args) || args.length != 6) {
            System.out.println("输入数据有误");
            return;
        }
        int[] birth = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        LocalDateTime birthday = LocalDateTime.of(birth[0], birth[1], birth[2], birth[3], birth[4], birth[5]);
        LocalDateTime current = LocalDateTime.now();
        int hours = current.getHour() - birthday.getHour();
        int minutes = current.getMinute() - birthday.getMinute();
        if (minutes < 0) {
            hours -= 1;
            minutes += 60;
        }
        int days = current.getDayOfMonth() - birthday.getDayOfMonth();
        if (hours < 0) {
            days -= 1;
            hours += 24;
        }
        int month = current.getMonthValue() - birthday.getMonthValue();
        if (days < 0) {
            month -= 1;
            days += current.minusMonths(1).toLocalDate().lengthOfMonth();
        }
        int year = current.getYear() - birthday.getYear();
        if (month < 0) {
            year -= 1;
            month += 12;
        }
        StringBuilder builder = new StringBuilder("我");
        if (year > 0) {
            builder.append(String.format("%s岁", year));
        }
        if (month > 0) {
            builder.append(String.format("%s个月", month));
        }
        if (days > 0) {
            builder.append(String.format("%s天", days));
        }
        if (hours > 0) {
            builder.append(String.format("%s小时", hours));
        }
        if (minutes > 0) {
            builder.append(String.format("%s分钟", minutes));
        }
        System.out.println("当前时间是：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(current));
        System.out.println(builder.toString());
        Duration duration = Duration.between(birthday, current).abs();
        long actualDays = duration.toDays();
        int extra = current.toLocalTime().compareTo(birthday.toLocalTime()) < 0 ? 1 : 0;
        System.out.println(String.format("已经满%s天，第%s天啦", actualDays, actualDays + 1 + extra));
    }
}

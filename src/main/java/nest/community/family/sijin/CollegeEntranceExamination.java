package nest.community.family.sijin;

import java.time.LocalDate;
import java.time.Period;

public class CollegeEntranceExamination {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("今天是" + today);
        LocalDate examination = LocalDate.of(2038, 6, 7);
        Period sparePeriod = Period.between(today, examination);
        if (sparePeriod.isNegative()) {
            System.out.println("张思瑾已经参加了高考");
            return;
        }
        StringBuilder builder = new StringBuilder(20);
        builder.append("我距离高考还有");
        int years = sparePeriod.getYears();
        if (years > 0) {
            builder.append(String.format("%s年", years));
        }
        int months = sparePeriod.getMonths();
        if (months > 0) {
            builder.append(String.format("%s个月", months));
        }
        int days = sparePeriod.getDays();
        if (days > 0) {
            builder.append(String.format("%s天", days));
        }
        System.out.println(builder.toString());
        long daysToLeft = examination.toEpochDay() - today.toEpochDay();
        System.out.println(String.format("约合%s天", daysToLeft));
    }
}

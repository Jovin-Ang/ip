package chatbot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * This class provides utility methods for parsing and formatting date and time strings.
 *
 * @author Jovin Ang
 */
public class DateTimeParser {
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a").withLocale(Locale.US);

    /**
     * Parses a date/datetime string into LocalDateTime.
     * If only date is provided, sets default time as 23:59.
     *
     * @param input date/datetime string in format "yyyy-MM-dd" or "yyyy-MM-dd HH:mm"
     * @return LocalDateTime object
     * @throws DateTimeParseException if input format is invalid
     */
    public static LocalDateTime parse(String input) throws DateTimeParseException {
        input = input.trim();
        try {
            // Try parsing full datetime format first
            return LocalDateTime.parse(input, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                // If datetime parse fails, try parsing as date only
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                return date.atTime(23, 59); // Default time: 23:59
            } catch (DateTimeParseException dateError) {
                throw new DateTimeParseException(
                        "Please use format 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm'",
                        input, dateError.getErrorIndex());
            }
        }
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMATTER);
    }
}

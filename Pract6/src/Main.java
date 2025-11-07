import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        String rawHeaders = """
                From: John Doe <john@example.com>
                To: alice@gmail.com, bob@yahoo.com
                Subject: Cheap pills!!! Limited OFFER
                Date: Mon, 10 Oct 2024 13:55:36 +0300
                Message-ID: <1234@mail.example.com>
                Received: from mail.example.com (mail.example.com [192.0.2.1])
                 by mx.google.com with ESMTPS id qwerty123;
                 Mon, 10 Oct 2024 13:55:36 +0300
                Received: from relay.test.net (relay.test.net [203.0.113.5])
                 by mail.example.com with ESMTPS;
                 Mon, 10 Oct 2024 13:50:00 +0300
                Reply-To: spam@pharma.biz, promo@scam.biz
                """;

        Pattern SPAM_KEYWORDS = Pattern.compile("(offer|free|pills)", Pattern.CASE_INSENSITIVE);

        Pattern fromPattern = Pattern.compile("^From: (?<from>.+)$", Pattern.MULTILINE);
        Pattern toPattern = Pattern.compile("^To: (?<to>.+)$", Pattern.MULTILINE);
        Pattern subjPattern = Pattern.compile("^Subject: (?<subj>.+)$", Pattern.MULTILINE);
        Pattern receivedPattern = Pattern.compile(
                "Received:.*?\\[(?<ip>\\d{1,3}(\\.\\d{1,3}){3})\\].*?;\\s*(?<date>[^\\n]+)",
                Pattern.MULTILINE | Pattern.DOTALL
        );


        String from = matchGroup(rawHeaders, fromPattern, "from");
        String to = matchGroup(rawHeaders, toPattern, "to");
        String subject = matchGroup(rawHeaders, subjPattern, "subj");

        Matcher recMatcher = receivedPattern.matcher(rawHeaders);
        List<String> hops = new ArrayList<>();
        while (recMatcher.find()) {
            hops.add(recMatcher.group("ip") + " @ " + recMatcher.group("date"));
        }

        boolean isSpam = SPAM_KEYWORDS.matcher(subject).find();


        List<String> subjects = Arrays.asList(subject);
        double avgSubjectLen = subjects.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);

        // --- Вывод результатов ---
        System.out.println("FROM: " + from);
        System.out.println("TO: " + to);
        System.out.println("SUBJECT: " + subject);
        System.out.println("Received hops: " + hops);
        System.out.println("Spam? " + isSpam);
        System.out.println("Avg subject length: " + avgSubjectLen);
    }

    private static String matchGroup(String text, Pattern p, String group) {
        Matcher m = p.matcher(text);
        return m.find() ? m.group(group).trim() : "";
    }
}

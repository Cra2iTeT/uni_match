package com.Cra2iTeT.UniMatch;

import cn.hutool.core.util.DesensitizedUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UniMatchApplicationTests {

    @Test
    void contextLoads() {
    }


    public static void main(String[] args) {
    }

    public static int calculateLevenshteinDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[str1.length()][str2.length()];
    }

    public static double calculateSimilarity(String str1, String str2) {
        int distance = calculateLevenshteinDistance(str1, str2);
        int maxLength = Math.max(str1.length(), str2.length());

        return 1.0 - (double) distance / maxLength;
    }


}

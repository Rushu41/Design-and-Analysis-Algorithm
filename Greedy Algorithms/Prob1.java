// ✅ Problem 1: Activity Selection Problem
// 🧠 Question
// You are given n activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.

// 📥 Input
// An array start[] of size n representing start times.

// An array end[] of size n representing finish times.

// 🎯 Goal
// Return the maximum number of non-overlapping activities that can be performed.

// 💡 Explanation (Greedy Strategy)
// The greedy strategy here is:

// Sort the activities by their finish time.

// Select the activity with the earliest finish time first.

// Then, skip all overlapping activities and pick the next one that starts after the last selected activity ends.

// This works because selecting activities with the earliest finish time leaves room for more future activities.
import java.util.*;

public class Prob1 {
    static class Act {
        int st;
        int ed;

        Act(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }
    }

    static void solve(int[] st, int[] ed, int n) {
        Act[] act = new Act[n];
        for (int i = 0; i < n; i++) {
            act[i] = new Act(st[i], ed[i]);
        }

        Arrays.sort(act, Comparator.comparingInt(a -> a.ed));
        int cnt = 1;
        int lastend = act[0].ed;
        for (int i = 1; i < n; i++) {
            if (act[i].st > lastend) {
                cnt++;
                lastend = act[i].ed;
            }
        }
        System.out.println(cnt);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] st = new int[n];
        int[] ed = new int[n];
        for (int i = 0; i > n; i++) {
            st[i] = sc.nextInt();
            ed[i] = sc.nextInt();
        }

        solve(st, ed, n);
    }
}

public class Pensje {

    static int n;
    static int limit = 1000004;
    static int start = 0, end = 0;
    static int[] upper = new int[limit];
    static int[] salary = new int[limit];
    static int[] used = new int[limit];
    static int[] count_lower = new int[limit];
    static int[] lower = new int[limit];
    static int[] order = new int[limit];
    static int[] count_upper = new int[limit];

    static long endTime;
    static long startTime;

    public static void main(String[] args) {
        int p = 0;
        int z = 0;
//        try {
//            read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        startTime = System.nanoTime();
        Reader read = new Reader();
        n = read.nextInt();

        for (int i = 1; i <= n; i++) {
            p = read.nextInt();
            z = read.nextInt();
            upper[i] = p;
            salary[i] = z;
            if (upper[i] == i) {
                salary[i] = n;
            }
            if (salary[i] != 0) {
                upper[i] = n + 1;
            }
        }
        ++n;
        upper[n] = n;
        salary[n] = n;
        upper_number();
        used_salary();
        final_salary();
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
//        for (int j = 1; j < n; ++j) {
//                writer.write(Integer.toString(salary[j])+"\n");
////            System.out.println(salary[j]);
//        }
        StringBuffer sb = new StringBuffer();
        for(int j=1;j<n;j++){
            sb.append(salary[j]+"\n");
        }
        System.out.println(sb);
//            writer.close();
//        }catch(Exception e){
//            e.printStackTrace();
//            }

//        try {
//            Compare_Files();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        endTime = System.nanoTime() - startTime;
//        System.out.println("Endtieme is " + endTime/1000000);
    }
// Compare input and output
//    private static void Compare_Files() throws FileNotFoundException {
//        int  a = 0;
//        File file1 = new File("test.txt");
//        File file2 = new File("out_wyniki\\pen5ocen.out");
//        Scanner sc1 = new Scanner(file1);
//        Scanner sc2 = new Scanner(file2);
//
//       while(sc1.hasNext()){
//        String s1 = sc1.next();
//        String s2 = sc2.next();
//
//           if(!s1.equals(s2)){
//               System.out.println("Error at " + a);
//               System.out.println("dziala niepoprawnie");
//               break;
//
//           }
//           a++;
//       }
//
//       sc1.close();
//       sc2.close();
//
//
//    }



    public static void upper_number() {

        //liczenie wyzszych galezi

        for (int i = 1; i < n; ++i) {
            ++count_upper[upper[i]];
        }
        for (int i = 1; i < n; ++i) {
            if (count_upper[i] == 0)
                order[end++] = i;
        }
        while (start < end) {
            int cur = order[start++];
            int pre = upper[cur];
            if (salary[cur] == 0) {
                if (--count_upper[pre] == 0) {
                    order[end++] = pre;
                }
                count_lower[pre] += count_lower[cur] + 1;
            }
        }
    }

    public static void used_salary() {
        for (int i = 1; i < n; ++i)
            if (salary[i] != 0)
                used[salary[i]] = i;
            else if (lower[upper[i]] == 0)
                lower[upper[i]] = i;
            else
                lower[upper[i]] = -1;
    }

    public static void final_salary() {
        int i = 0;
        int free = 0, available = 0;
        while (i < n - 1) {
            while (i < n - 1 && used[i + 1] == 0) {
                ++i;
                ++free;
                ++available;
            }
            while (i < n - 1 && used[i + 1] != 0) {
                ++i;
                int a = used[i], l = i;
                free -= count_lower[a];
                if (free == 0) {
                    while (available-- != 0 && lower[a] > 0) {
                        while (used[l] != 0) {
                            --l;
                        }
                        a = lower[a];
                        salary[a] = l;
                        used[l] = a;
                    }
                    available = 0;
                }
                if (count_lower[a] != 0)
                    available = 0;
            }
        }

    }



}

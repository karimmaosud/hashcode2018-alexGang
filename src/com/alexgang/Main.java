package com.alexgang;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    BufferedReader reader = new BufferedReader(new FileReader("a_example.in"));
//    BufferedReader reader = new BufferedReader(new FileReader("b_should_be_easy.in"));
//    BufferedReader reader = new BufferedReader(new FileReader("c_no_hurry.in"));
//    BufferedReader reader = new BufferedReader(new FileReader("d_metropolis.in"));
    BufferedReader reader = new BufferedReader(new FileReader("e_high_bonus.in"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
    String[] in = reader.readLine().split(" ");
    int R, C, F, N, B, T;

    R = Integer.parseInt(in[0]);
    C = Integer.parseInt(in[1]);
    F = Integer.parseInt(in[2]);
    N = Integer.parseInt(in[3]);
    B = Integer.parseInt(in[4]);
    T = Integer.parseInt(in[5]);

    Ride[] ride = new Ride[N];


    for (int i = 0; i < N; i++) {
      ride[i] = new Ride();
      in = reader.readLine().split(" ");
      ride[i].a = Integer.parseInt(in[0]);
      ride[i].b = Integer.parseInt(in[1]);
      ride[i].x = Integer.parseInt(in[2]);
      ride[i].y = Integer.parseInt(in[3]);
      ride[i].s = Integer.parseInt(in[4]);
      ride[i].f = Integer.parseInt(in[5]);
    }




    bruteforce(R, C, F, N, B, T, ride, writer);


  }

  private static void bruteforce(int r, int c, int f, int n, int b, int t, Ride[] ride, BufferedWriter writer) throws IOException {

    Arrays.sort(ride);

    ArrayList<Integer>[] driver_rides = new ArrayList[f];

    for (int i = 0; i < f; ++i) {
      driver_rides[i] = new ArrayList<>();
    }


    for (int i = 0; i < n; ++i) {
      driver_rides[i % f].add(i);
    }



    for (int i = 0; i < f; ++i) {
      int num_rides = driver_rides[i].size();
      writer.append(num_rides+"");
      for (int j = 0; j < num_rides; ++ j) {
        writer.append(" " + driver_rides[i].get(j));
      }
      writer.append("\n");
    }

    writer.close();
  }

  static class Ride implements Comparable<Ride> {
    public int a, b, x, y, s, f;

    public Ride(int a, int b, int x, int y, int s, int f) {
      this.a = a;
      this.b = b;
      this.x = x;
      this.y = y;
      this.s = s;
      this.f = f;
    }

    public Ride() {
    }

    @Override
    public int compareTo(Ride o) {
      if (this.s != o.s) {
        return this.s - o.s;
      } else {
        if (this.f != o.f) {
          return this.f - o.f;
        } else {
          if (this.dist_origin() != o.dist_origin()) {
            return this.dist_origin() - o.dist_origin();
          } else {
            if (this.manhatten() != o.manhatten()) {
              return o.manhatten() - this.manhatten();
            } else {
              return 0;
            }
          }
        }
      }
    }

    public int manhatten() {
      return Math.abs(a-x) + Math.abs(b-y);
    }

    public int dist_origin() {
      return a + b;
    }
  }
}


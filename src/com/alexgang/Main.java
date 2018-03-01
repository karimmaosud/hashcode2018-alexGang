package com.alexgang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        if (this.dist_origin() != o.dist_origin()) {
          return this.dist_origin() - o.dist_origin();
        } else {
          if (this.manhatten() != o.manhatten()) {
            return o.s - this.s;
          } else {
            return 0;
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


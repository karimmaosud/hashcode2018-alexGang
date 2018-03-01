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
//    bruteforce(R, C, F, N, B, T, ride, writer);
    solution1(R, C, F, N, B, T, ride, writer);
  }


  private static void solution1(int r, int c, int f, int n, int b, int t, Ride[] rides, BufferedWriter writer) throws IOException {
    Driver[] drivers = new Driver[f];
    for (int i = 0; i < f; ++i) {
      drivers[i] = new Driver();
    }

    for(int i = 0; i < rides.length; i++){
      Driver assigned = null;
      int minimumTime = Integer.MAX_VALUE;
      for(Driver driver: drivers){
        int time = getDispatchTime(driver, rides[i]);
        if(time < minimumTime){
          minimumTime = time;
          assigned = driver;
        }
      }
      if(assigned != null){
        updateDrive(assigned, rides[i], minimumTime, i);
      }

    }
    print(drivers, writer);
  }

  private static void updateDrive(Driver assigned, Ride ride, int minimumTime, int rideIndex) {
    assigned.t += minimumTime + ride.manhatten();
    assigned.x = ride.x;
    assigned.y = ride.y;
    assigned.driver_rides.add(rideIndex);
  }

  private static int getDispatchTime(Driver driver, Ride ride) {
    int manhaten = Math.abs(driver.x - ride.a) + Math.abs(driver.y - ride.b);
    int arrivalTime = manhaten + driver.t;

    if(arrivalTime + ride.manhatten() > ride.f){
      return Integer.MAX_VALUE;
    }
    int waitingTime = ride.s >= arrivalTime? ride.s - arrivalTime: 0;
    return manhaten + waitingTime;
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

  private static void print(Driver[] drivers, BufferedWriter writer) throws IOException {
    for (int i = 0; i < drivers.length; ++i) {
      int num_rides = drivers[i].driver_rides.size();
      writer.append(num_rides+"");
      for (int j = 0; j < num_rides; ++ j) {
        writer.append(" " + drivers[i].driver_rides.get(j));
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

  static class Driver{
    int x;
    int y;
    int t;
    ArrayList<Integer> driver_rides;
    public Driver(){
      driver_rides = new ArrayList<>();
    }
  }
}


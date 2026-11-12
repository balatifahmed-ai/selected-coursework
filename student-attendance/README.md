# Student attendance

The console accepts student data from a file or manual input and exports attendance records and class totals. Teacher.java contains Ahmed’s attendance and reporting methods. The main program demonstrates import, display, analytics and export.

## Try it

From this folder:

```sh
javac -d build *.java
printf '1\nstudents.txt\n' | java -cp build cs102project.Main
```

One fictional student is marked present. The program displays 100% attendance and writes new_students.txt in the current folder, replacing any previous export there. The original summary logic counts matching records across the supplied data; its “today” label assumes a single-day input.

## Credits and edition

CS102 team project. Ahmed Balatif wrote Teacher and integrated the classes in Main. Saud Bin Jarallah wrote AttendanceRecord and Admin. Ziyad Al Ali wrote Student and the report. This copy substitutes a fictional teacher and writes the export locally.

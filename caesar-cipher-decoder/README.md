# Caesar cipher decoder

Which shift produces readable English? The program tries the shifts in a custom alphabet of letters, digits and punctuation. It accepts the first decoding with five common-word matches.

## Try it

From this folder:

```sh
javac -d build CS285_Project.java
printf 'ciphertext.txt\n1\n' | java -cp build cs285_project.CS285_Project
```

The first line decodes to “we have the key and it is in the box” with key 3. The second line is too short to meet the five-match threshold and is skipped. Repeated runs create numbered output files beside the input.

## Credits and edition

CS285 team project by Ahmed Balatif and Ziyad Al Ali. This portfolio copy uses the class-matching source from the final pair, removes student IDs, uses a portable output path and limits the search to one rotation of the alphabet. The submitted version could run past the alphabet on an unmatched line.

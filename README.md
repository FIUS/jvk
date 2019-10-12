# jvk
Sheets and project for the annual Java-Vorkurs of the Fachgruppe Informatik der Universität Stuttgart

## Compiling Exercise Sheets (LaTeX)

Each exercise sheet can be compiled standalone using `pdflatex`.
This means that it *just works* with most LaTeX IDEs.

### Using `make`

For compilation from the command line, we use GNU `make` and internally `latexmk` which is usually
included in a LaTeX distribution.

```bash
$ cd sheets
$ make 1/jvk-blatt1.pdf # Compiles the exercise sheet
$ make 1/jvk-blatt1-lsg.pdf # Compiles the solution sheet
$ make build # Compiles all exercise sheets and solution sheets
$ make clean # Deletes LaTeX intermediate files
$ make # Like `make build` followed by `make clean`
$ make cleanall # Deletes LaTeX intermediate files and output files (PDFs)
```

### Manually (without `make`)

```bash
$ cd sheets/1
$ latexmk -pdf jvk-blatt1.tex # Compiles the exercise sheet
$ # Compiles the solution sheet:
$ latexmk -pdf -pdflatex='pdflatex %O "\def\jvksolutions{} \input{%S}"' -jobname=jvk-blatt1-lsg jvk-blatt1.tex
```

## License

The contents of this repository are distributed under the
[GNU Affero General Public License](./LICENSE), except for those files which
declare a different licensing within their content, and except for the logos
located in `./sheets/logos`.

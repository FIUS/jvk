# jvk
Sheets and project for the annual Java-Vorkurs of the Fachgruppe Informatik der Universität Stuttgart

## Compiling Exercise Sheets (LaTeX)

Each exercise sheet can be compiled standalone using `pdflatex`.
This means that it *just works* with most LaTeX IDEs.

### Using GNU Make

For compilation from the command line, we use GNU `make` and internally `latexmk` which is usually
included in LaTeX distributions.

```bash
$ cd sheets
$ make 1/jvk-blatt1.pdf # Compiles the exercise sheet
$ make 1/jvk-blatt1-lsg.pdf # Compiles the solution sheet
$ make build # Compiles all exercise sheets and solution sheets
$ make clean # Deletes LaTeX intermediate files
$ make # Like `make build` followed by `make clean`
$ make cleanall # Deletes LaTeX intermediate files and output files (PDFs)
```

### Manually

Compilation without `make` might be needed for debugging the Makefile or if you're forced to use
Windows.
The following works in `bash`, Windows `git-bash` and Windows PowerShell:

```bash
$ cd sheets/1
$ # Compiles the exercise sheet:
$ latexmk -pdf jvk-blatt1.tex
$ # Compiles the solution sheet:
$ latexmk -pdf '-pdflatex=pdflatex %O "\def\jvksolutions{}\input" %S' -jobname=jvk-blatt1-lsg jvk-blatt1.tex
```

And the following works in Windows `cmd`:

```bat
cd sheets/1
:: Compiles the exercise sheet:
latexmk -pdf jvk-blatt1.tex
:: Compiles the solution sheet:
latexmk -pdf "-pdflatex=pdflatex %O \def\jvksolutions{}\input %S" -jobname=jvk-blatt1-lsg jvk-blatt1.tex
```

## License

The contents of this repository are distributed under the
[GNU Affero General Public License](./LICENSE), except for those files which
declare a different licensing within their content, and except for the logos
located in `./sheets/logos`.

TEX_DEPS_COMMON = $(wildcard *.tex)
TEX_INPUTS = $(wildcard */jvk-blatt*.tex)
PDF_OUTPUTS = $(TEX_INPUTS:.tex=.pdf)

all: build clean

build: $(PDF_OUTPUTS)

clean:
	rm -f */jvk-blatt*.{aux,bbl,blg,glo,idx,log,toc,out,synctex.gz,fls,fdb_latexmk}

cleanall: clean
	rm -f */jvk-blatt*.{ps,pdf,dvi}

.SECONDEXPANSION:

%.pdf: $$(@D)/*.tex $(TEX_DEPS_COMMON)
	cd $(@D) && latexmk -pdf $(*F).tex


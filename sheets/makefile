TEX_DEPS_COMMON = $(wildcard *.tex)
TEX_INPUTS = $(wildcard */jvk-blatt*.tex)
PDF_OUTPUTS = $(TEX_INPUTS:.tex=.pdf)
PDF_OUTPUTS_LSG = $(TEX_INPUTS:.tex=-lsg.pdf)

all: build clean

build: $(PDF_OUTPUTS) $(PDF_OUTPUTS_LSG)

clean:
	rm -f */jvk-blatt*.{aux,bbl,blg,glo,idx,log,toc,out,synctex.gz,fls,fdb_latexmk}

cleanall: clean
	rm -f */jvk-blatt*.{ps,pdf,dvi}

.SECONDEXPANSION:

%.pdf: $$(@D)/*.tex $(TEX_DEPS_COMMON)
	cd $(@D) && latexmk -pdf $(*F).tex


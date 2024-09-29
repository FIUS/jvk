\NeedsTeXFormat{LaTeX2e}

\ProvidesClass{vorkurs}
\LoadClass[a4paper,11pt]{article}

%% Import settings for links, names and file names
\input{../vorkurs-settings.cls}


%%%%%%%%%%%%
% Packages %
%%%%%%%%%%%%

%% Base font decoding
\RequirePackage{mmap}
\RequirePackage[nomath]{lmodern}
\RequirePackage[utf8]{inputenc}
\RequirePackage[T1]{fontenc}
\RequirePackage[ngerman]{babel}




%% Page formatting
%\RequirePackage[margin=3cm]{geometry}
\RequirePackage[
	a4paper,
	lmargin = {2cm},
	rmargin = {2cm},
	tmargin = {2cm},
	bmargin = {1.5cm},
	includeheadfoot
]{geometry}
\RequirePackage{fancyhdr}

%% "Default" Document Packages
\RequirePackage{float} %[H] option prevents floating
\RequirePackage{csquotes} %Quotation with \enquote{} command
\RequirePackage{graphicx}
\RequirePackage{listings}
\RequirePackage[shortlabels]{enumitem}
\RequirePackage{hyperref}
\RequirePackage[fixpdftex,dvipsnames]{xcolor}
\RequirePackage{pdfpages}
\RequirePackage[fleqn]{mathtools}
\RequirePackage{amssymb}

%% "Advanced" Document Packages
\RequirePackage{tikz}
\RequirePackage{minibox}
\RequirePackage{lastpage}
\usetikzlibrary{positioning,backgrounds}

%% For the infoboxes
\RequirePackage[many]{tcolorbox}
\RequirePackage[framemethod=tikz]{mdframed}
\RequirePackage[tikz]{bclogo}
\usetikzlibrary{matrix}
\tcbuselibrary{listings,breakable}

%% Testing...
\RequirePackage{lipsum}
%\RequirePackage{todo}
\RequirePackage{dirtytalk}


%%%%%%%%%%%%%%%%%%%
%  Package Setup  %
%%%%%%%%%%%%%%%%%%%

%% Hyperref Setup
\definecolor{linkColor}{HTML}{0000FF}
\hypersetup{
	allbordercolors=linkColor,
	pdfborderstyle={/S/U/W 1},
	pdfsubject=\kurs,
	pdfkeywords={\kurs,\semester},
	unicode
}

%% Fancyhdr Header
\pagestyle{myheadings}
\fancyhead[L]{\kurs~\semester~- Übungsblatt \sheetNumber}
\fancyhead[R]{Seite \thepage{} von \pageref*{LastPage}}

%% Colors for Java listings
\definecolor{javaKeywords}{HTML}{000080}
\definecolor{javaTypes}{HTML}{000000}
\definecolor{javaInstances}{HTML}{660E7A}
\definecolor{javaString}{rgb}{0.6,0,0}
\definecolor{javaComments}{HTML}{606060}
\definecolor{unimportentgrey}{rgb}{0.6,0.6,0.6}

%% Configuration of Java listings
\lstset{
	% Latin-1 compatibility:
	literate={Ö}{{\"O}}1{Ä}{{\"A}}1{Ü}{{\"U}}1{ß}{{\ss}}1{ü}{{\"u}}1{ä}{{\"a}}1{ö}{{\"o}}1,		% Me LaTeX do not know UNICODE
	% Formatting
	basicstyle={\ttfamily\small},
	language=Java,
	% Syntax Highlithing
	keywordstyle={\bfseries\color{javaKeywords}},
	otherkeywords={},
	keywordstyle=[2]{\color{javaTypes}},	%\itshape %% Nicht zu 100% zufrieden
	keywords=[2]{String,Game,DemoTask,RandomClass,Position,Dog,Hund,Simulation,Coin,Position,DemoTaskVerifier,Main},	% Wenn ich "PlayfieldModifier" hinzufüge, passieren seltsame Dinge bei Blatt 1 Ex 4... und ich habe keine Ahnung warum.
	identifierstyle={\bfseries\color{javaInstances}},
	stringstyle={\color{javaString}},		%\bfseries
	commentstyle={\color{javaComments}},
	morecomment=[l]{*},
	% Code Lines
	numbers=left,
	numberstyle=\footnotesize\color{javaComments},
	stepnumber=1,
	numbersep=10pt,
	% Misc
	tabsize=4,
	showspaces=false,
	showstringspaces=false,
	frame=single,
	breaklines=true,
	postbreak=\mbox{\textcolor{unimportentgrey}{$\hookrightarrow$}\space},
	escapechar=§
}


%%%%%%%%%%%%%%%%%%%%%%%
% Customized Commands %
%%%%%%%%%%%%%%%%%%%%%%%


%% Main Title
\renewcommand{\maketitle}{
	\thispagestyle{plain}
	\includegraphics[height=1cm]{../logos/logo-uni.pdf}
	\hfill\includegraphics[height=3cm]{../logos/fius-logo-neu.png}
	\begin{center}
	\Large\textbf{Übungsblatt \sheetNumber}\\
	\large\kurs (\semester)
	\normalsize
	\end{center}
}

%% Quotation
\newcommand{\q}[1]{\glqq#1\grqq\ } %German quotation - TODO: Mit \enquote ersetzen

%% "Optional" Command
\definecolor{optional}{HTML}{CC7832}
\newcommand{\optional}{{\color{javaString}\textbf{(Optional)} }} % mark optional tasks (includes space)

%% (Subjective) better to read line breaks
\renewcommand{\newline}{\vskip0.15cm}

\newcommand{\hint}{\textit{Hinweis:}~}
\newcommand{\important}{{\color{red}\bfseries Wichtig:}~}

%%%%%%%%%%%%%%%%%%%%
% Excercise titles %
%%%%%%%%%%%%%%%%%%%%

\newcounter{excerciseCounter}\setcounter{excerciseCounter}{1}
\newcommand{\excercise}[1]{
	\ifthenelse{\value{excerciseCounter}=1}{}{
		\vspace{2em}
	}
	\section{-\quad#1}
	%\large\textbf{Aufgabe \arabic{excerciseCounter}:} #1\normalsize\\
	\medskip
}
\makeatletter
\renewcommand\thesection{Aufgabe \@arabic\c@section}
\makeatother
\setenumerate{label=\alph*)}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Automatic Document inclusion %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

\newcommand{\addexcercise}{
	\IfFileExists{exercise-\arabic{excerciseCounter}.tex}{
		\begin{samepage}
	    	\input{exercise-\arabic{excerciseCounter}.tex}
	    	\pagebreak[4]
		\end{samepage}
		\stepcounter{excerciseCounter}
		\addexcercise
	}{}
}


%%%%%%%%%%%
% Infobox %
%%%%%%%%%%%

\mdfdefinestyle{mystyle}{%
	rightline=true,
	innerleftmargin=10,
	innerrightmargin=10,
	outerlinewidth=2pt,
	topline=false,
	rightline=true,
	bottomline=false,
	skipabove=\topsep,
	skipbelow=\topsep
}

\newtcolorbox{myboxi}[1][]{
	breakable,
	title=#1,
	colback=white,
	colbacktitle=white,
	coltitle=black,
	fonttitle=\bfseries,
	bottomrule=0pt,
	toprule=0pt,
	leftrule=3pt,
	rightrule=3pt,
	titlerule=0pt,
	arc=0pt,
	outer arc=0pt,
	colframe=black,
}

\newtcolorbox{Infobox}[1][]{
	breakable,
	freelance,
	title=#1,
	colback=white,
	colbacktitle=white,
	coltitle=black,
	fonttitle=\bfseries,
	bottomrule=0pt,
	boxrule=0pt,
	colframe=white,
	overlay unbroken and first={
		\draw[color=javaInstances,line width=2pt]
		([xshift=5pt]frame.north west) --
		(frame.north west) --
		(frame.south west);
		\draw[color=javaInstances,line width=2pt]
		([xshift=-5pt]frame.north east) --
		(frame.north east) --
		(frame.south east);
	},
	overlay unbroken app={
		\draw[color=javaInstances,line width=2pt,line cap=rect]
		(frame.south west) --
		([xshift=5pt]frame.south west);
		\draw[color=javaInstances,line width=2pt,line cap=rect]
		(frame.south east) --
		([xshift=-5pt]frame.south east);
	},
	overlay middle and last={
		\draw[color=javaInstances,line width=2pt]
		(frame.north west) --
		(frame.south west);
		\draw[color=javaInstances,line width=2pt]
		(frame.north east) --
		(frame.south east);
	},
	overlay last app={
		\draw[color=javaInstances,line width=2pt,line cap=rect]
		(frame.south west) --
		([xshift=5pt]frame.south west);
		\draw[color=javaInstances,line width=2pt,line cap=rect]
		(frame.south east) --
		([xshift=-5pt]frame.south east);
	},
}


%%%%%%%%%%%%%%%%%%%%%%
% document structure %
%%%%%%%%%%%%%%%%%%%%%%

\def\prepart{}
\newcommand{\preamble}[1]{\def\prepart{#1}}
\newcommand{\makedocument}{
	\hypersetup{
		pdftitle=\kurs~\semester~-~Übungsblatt~\sheetNumber
	}
	\begin{document}
		\maketitle
		\prepart
		\pagestyle{fancy}
		\IfFileExists{intro.tex}{
			\begin{samepage}
				\input{intro.tex}
			\end{samepage}
		}{}
		\addexcercise
	\end{document}
}
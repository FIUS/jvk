\NeedsTeXFormat{LaTeX2e}

\ProvidesClass{vorkurs}
\LoadClass[a4paper]{article}

% Settings
%
% Space for Settings that can and should be changed
\def\kurs{Java Vorkurs} %Name of the kurse
\def\semester{WS 2021} %Number of the Semester


%%%%%%%%%%%%
% Packages %
%%%%%%%%%%%%
\RequirePackage[ngerman]{babel}
\RequirePackage[utf8]{inputenc}
\RequirePackage{tikz}
\RequirePackage{minibox}
\RequirePackage[margin=3cm]{geometry}
\RequirePackage{graphicx}
\RequirePackage{pdfpages}
\RequirePackage{fancyhdr}
\RequirePackage{lastpage}
\RequirePackage{todo}
\RequirePackage{listings}
\RequirePackage{enumitem}
\RequirePackage{hyperref}
\RequirePackage{xcolor}
\usetikzlibrary{positioning,backgrounds}

%infobox
\RequirePackage[many]{tcolorbox}
\RequirePackage[framemethod=tikz]{mdframed}
\RequirePackage{xcolor}
\RequirePackage[tikz]{bclogo}
\usetikzlibrary{matrix}
\tcbuselibrary{listings,breakable}

%%%%%%%%%%%%
%  colors  %
%%%%%%%%%%%%
\definecolor{javared}{rgb}{0.6,0,0} % for strings
\definecolor{javagreen}{rgb}{0.25,0.5,0.35} % comments
\definecolor{javapurple}{rgb}{0.5,0,0.35} % keywords
\definecolor{javadocblue}{rgb}{0.25,0.35,0.75}
\definecolor{javablue}{rgb}{0.0,0.0,1.0}
\definecolor{unimportentgrey}{rgb}{0.6,0.6,0.6}
%%%%%%%%%%%%
% listings %
%%%%%%%%%%%%
\lstset{
  literate=%
  {Ö}{{\"O}}1
  {Ä}{{\"A}}1
  {Ü}{{\"U}}1
  {ß}{{\ss}}1
  {ü}{{\"u}}1
  {ä}{{\"a}}1
  {ö}{{\"o}}1,
  language=Java,
  basicstyle=\ttfamily,
  keywordstyle=\color{javapurple}\bfseries,
  stringstyle=\color{javared},
  commentstyle=\color{javagreen},
  identifierstyle=\color{javablue},
  morecomment=[s][\color{javadocblue}]{/**}{*/},
  numbers=left,
  numberstyle=\footnotesize\color{black},
  stepnumber=1,
  numbersep=10pt,
  tabsize=4,
  showspaces=false,
  showstringspaces=false,
  frame=single,
  breaklines=true,
  postbreak=\mbox{\textcolor{unimportentgrey}{$\hookrightarrow$}\space},
  escapechar=§
}

%%%%%%%%%%%%%%
% Main Title %
%%%%%%%%%%%%%%
\renewcommand{\maketitle}{
  \thispagestyle{plain}
  \includegraphics[height=1cm]{../logos/logo-uni.pdf}
  \hfill\includegraphics[height=3cm]{../logos/logo-fius.png}
  \begin{center}
  \Large\textbf{Übungsblatt \sheetNumber}\\
  \large\kurs (\semester)
  \normalsize
  \end{center}
}

%%%%%%%%%%%%%%%%%%%%
% Excercise titles %
%%%%%%%%%%%%%%%%%%%%
\newcounter{excerciseCounter}\setcounter{excerciseCounter}{1}
\newcommand{\excercise}[1]{
  \ifthenelse{\value{excerciseCounter}=1}{}{
  \vspace{2em}
  }
  \section{#1}
  %\large\textbf{Aufgabe \arabic{excerciseCounter}:} #1\normalsize\\
  \medskip
}
\makeatletter
\renewcommand\thesection{Aufgabe \@arabic\c@section:}
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



%%%%%%%%%%
% header %
%%%%%%%%%%
        \pagestyle{myheadings}
        \fancyhead[L]{\kurs \ (\semester) - Übungsblatt \sheetNumber}
        \fancyhead[R]{Seite \thepage von \pageref{LastPage}}
        %\rightmark{test}
        %\runningheadrule
% \runningheader{\coursename\ (\semestershort) -- \"Ubungsblatt \exercisenumber\ \ifprintanswers{\textbf{{\color{red}{ \fbox{L\"osung}}}}}\fi}{}{Seite \thepage\ von \numpages}

%%%%%%%%%%%
% Infobox %
%%%%%%%%%%%
\definecolor{bgblue}{RGB}{245,243,253}
\definecolor{ttblue}{RGB}{91,194,224}

\mdfdefinestyle{mystyle}{%
  rightline=true,
  innerleftmargin=10,
  innerrightmargin=10,
  outerlinewidth=3pt,
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
  \draw[blue!75!black,line width=3pt]
    ([xshift=5pt]frame.north west) --
    (frame.north west) --
    (frame.south west);
  \draw[blue!75!black,line width=3pt]
    ([xshift=-5pt]frame.north east) --
    (frame.north east) --
    (frame.south east);
  },
  overlay unbroken app={
  \draw[blue!75!black,line width=3pt,line cap=rect]
    (frame.south west) --
    ([xshift=5pt]frame.south west);
  \draw[blue!75!black,line width=3pt,line cap=rect]
    (frame.south east) --
    ([xshift=-5pt]frame.south east);
  },
  overlay middle and last={
  \draw[blue!75!black,line width=3pt]
    (frame.north west) --
    (frame.south west);
  \draw[blue!75!black,line width=3pt]
    (frame.north east) --
    (frame.south east);
  },
  overlay last app={
  \draw[blue!75!black,line width=3pt,line cap=rect]
    (frame.south west) --
    ([xshift=5pt]frame.south west);
  \draw[blue!75!black,line width=3pt,line cap=rect]
    (frame.south east) --
    ([xshift=-5pt]frame.south east);
  },
}
\renewcommand\bcStyleTitre[1]{\large\textcolor{ttblue}{#1}}


%%%%%%%%%%%%%%%%%%%%%%
% document structure %
%%%%%%%%%%%%%%%%%%%%%%
\def\prepart{}
\newcommand{\preamble}[1]{\def\prepart{#1}}
\newcommand{\makedocument}{
  \begin{document}
  \maketitle
  \prepart
  \pagestyle{fancy}
      \addexcercise
    \end{document}
  }

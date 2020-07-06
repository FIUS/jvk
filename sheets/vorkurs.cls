\NeedsTeXFormat{LaTeX2e}

\ProvidesClass{vorkurs}
\LoadClass[a4paper]{article}

% Settings
%
% Space for Settings that can and should be changed
\def\kurs{Java Vorkurs} %Name of the kurse
\def\semester{WS 2021} %Number of the Semester


%
% Packages
%
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
  {ö}{{\"o}}1
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
  \large\textbf{Aufgabe \arabic{excerciseCounter}:} #1\normalsize\\
  \medskip
}
\newcounter{subexcerciseCounter}
\newcommand{\subexcercise}{\stepcounter{subexcerciseCounter}\alph{subexcerciseCounter}}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Automatic Document inclusion %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\newcommand{\addexcercise}{
    \IfFileExists{exercise-\arabic{excerciseCounter}.tex}{
        \begin{samepage}
          \input{exercise-\arabic{excerciseCounter}.tex}
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

%document structure
\newcommand{\makedocument}{
  \begin{document}
  \maketitle
  \pagestyle{fancy}
        \addexcercise
    \end{document}
  }

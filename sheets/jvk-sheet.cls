\NeedsTeXFormat{LaTeX2e}

\ProvidesClass{jvk-sheet}
\LoadClass[a4paper]{article}


%    
% Packages
%
\RequirePackage{tikz}
\usepackage{minibox}
\usepackage[margin=3cm]{geometry}

\newcounter{excerciseCounter}\setcounter{excerciseCounter}{1}
% titles
\newcommand{\excercise}[1]{\section*{Aufgabe \arabic{excerciseCounter}: #1}}
\newcounter{subexcerciseCounter}
\newcommand{\subexcercise}{\stepcounter{subexcerciseCounter}\alph{subexcerciseCounter}}

%document structure
\newcommand{\addexcercise}{
    \IfFileExists{excercise-\arabic{excerciseCounter}.tex}{
        \begin{samepage}
        \input{excercise-\arabic{excerciseCounter}.tex}
        \end{samepage}
        \stepcounter{excerciseCounter}
        \addexcercise
    }{}
}
\newcommand{\makedocument}{
    \begin{document}
        \addexcercise
    \end{document}
}
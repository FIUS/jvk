let
  fallbackPkgs = import <nixpkgs> {};
in

{
  stdenv ? fallbackPkgs.stdenv,

  maven ? fallbackPkgs.maven,
  openjdk11 ? fallbackPkgs.openjdk11,

  gnumake ? fallbackPkgs.gnumake,
  texlive ? fallbackPkgs.texlive,
}:

stdenv.mkDerivation {
  name = "jvk-shell";
  nativeBuildInputs = [
    (maven.override { jdk = openjdk11; } )
    openjdk11

    gnumake
    texlive.combined.scheme-full
  ];
}

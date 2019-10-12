let
  fallbackPkgs = import <nixpkgs> {};
in

{
  maven ? fallbackPkgs.maven,
  openjdk11 ? fallbackPkgs.openjdk11,
  stdenv ? fallbackPkgs.stdenv,
}:

stdenv.mkDerivation {
  name = "icge2-shell";
  buildInputs = [
    openjdk11
    (maven.override { jdk = openjdk11; } )
  ];
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entities;

/**
 *
 * @author dudam
 */
public enum Competencia {
    PYTHON("Python"),
    C_SHARP("C#"),
    C_PLUS_PLUS("C++"),
    JS("JS"),
    PHP("PHP"),
    SWIFT("Swift"),
    JAVA("Java"),
    GO("Go"),
    SQL("SQL"),
    RUBY("Ruby"),
    HTML("HTML"),
    CSS("CSS"),
    NOSQL("NOSQL"),
    FLUTTER("Flutter"),
    TYPESCRIPT("TypeScript"),
    PERL("Perl"),
    COBOL("Cobol"),
    DOT_NET("dotNet"),
    KOTLIN("Kotlin"),
    DART("Dart");

    private final String displayName;

    Competencia(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
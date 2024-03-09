package com.saha.amit.f_dependencyInjectionTypes;

class DependencyA{
    private DependencyB dependencyB;

    public DependencyA(DependencyB dependencyB) {
        this.dependencyB = dependencyB;
    }
}

class DependencyB{
    private DependencyA dependencyA;

    public DependencyB(DependencyA dependencyA) {
        this.dependencyA = dependencyA;
    }

    public void setDependencyA(DependencyA dependencyA) {
        this.dependencyA = dependencyA;
    }
}

class DependencyC{}
public class Demo {
    private DependencyA dependencyA;
    private DependencyB dependencyB;

    private DependencyC dependencyC;

    // When we have mandatory dependency it's better to use Constructor injection as it ensures Dependency are present during object creation
    public Demo(DependencyA dependencyA, DependencyB dependencyB, DependencyC dependencyC) {
        this.dependencyA = dependencyA;
        this.dependencyB = dependencyB;
        this.dependencyC = dependencyC;
    }

    // If the dependency are not mandatory better to use setter injection. As the object will be created first then the dependency
    public void setDependencyA(DependencyA dependencyA) {
        this.dependencyA = dependencyA;
    }

    public void setDependencyB(DependencyB dependencyB) {
        this.dependencyB = dependencyB;
    }

    public void setDependencyC(DependencyC dependencyC) {
        this.dependencyC = dependencyC;
    }
}

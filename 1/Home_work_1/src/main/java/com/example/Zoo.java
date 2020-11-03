package com.example;

import com.example.Cat;
import com.example.Dog;

@SimpleComponent
public class Zoo {

    @AutowireSimpleComponent
    private Cat cat;
    @AutowireSimpleComponent
    private Dog dog;
    @AfterDependenciected
    private void makeNoise() {
        cat.meov();
        dog.gav();
    }

    public Zoo(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }

    public Zoo() {
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}

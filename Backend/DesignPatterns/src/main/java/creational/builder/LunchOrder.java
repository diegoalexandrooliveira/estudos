package creational.builder;

public class LunchOrder {

    public static class Builder {

        private String bread;
        private String condiments;

        public Builder() {
        }

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder condiments(String condiments) {
            this.condiments = condiments;
            return this;
        }

        public LunchOrder build() {
            return new LunchOrder(this);
        }


    }

    private LunchOrder(Builder builder) {
        this.bread = builder.bread;
        this.condiments = builder.condiments;
    }


    private final String bread;
    private final String condiments;

    public String getBread() {
        return bread;
    }

    public String getCondiments() {
        return condiments;
    }

    @Override
    public String toString() {
        return "LunchOrder{" +
                "bread='" + bread + '\'' +
                ", condiments='" + condiments + '\'' +
                '}';
    }
}

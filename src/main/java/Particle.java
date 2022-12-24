public record Particle(int x, int y, int z) {
    public Particle(String line) {
        this(Integer.parseInt(line.split(",")[0]),
                Integer.parseInt(line.split(",")[1]),
                Integer.parseInt(line.split(",")[2]));
    }

    public Particle(Particle source, int dx, int dy, int dz) {
        this(source.x + dx, source.y + dy, source.z + dz);
    }

    public Particle x(int x) {
        return new Particle(x, y, z);
    }

    public Particle y(int y) {
        return new Particle(x, y, z);
    }

    public Particle z(int z) {
        return new Particle(x, y, z);
    }
}

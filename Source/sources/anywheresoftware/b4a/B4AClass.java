package anywheresoftware.b4a;

public interface B4AClass {
    boolean IsInitialized();

    BA getActivityBA();

    BA getBA();

    public static abstract class ImplB4AClass implements B4AClass {
        public BA ba;
        protected ImplB4AClass mostCurrent;

        @Override // anywheresoftware.b4a.B4AClass
        public BA getBA() {
            return this.ba;
        }

        @Override // anywheresoftware.b4a.B4AClass
        public BA getActivityBA() {
            BA aba = null;
            if (this.ba.sharedProcessBA.activityBA != null) {
                aba = this.ba.sharedProcessBA.activityBA.get();
            }
            if (aba == null) {
                return this.ba;
            }
            return aba;
        }

        public String toString() {
            return BA.TypeToString(this, true);
        }

        @Override // anywheresoftware.b4a.B4AClass
        public boolean IsInitialized() {
            return this.ba != null;
        }
    }
}

package cn.techwolf.data.fanshe;

import java.lang.reflect.*;

/**
 * @author yl.xing
 * @create:2020-04-23
 * @describe
 **/
public abstract class ParentUser<E,D,F> {

    private int i=0;

    protected ParentUser(){

        this(2);
    }

    protected ParentUser(int i){
        System.out.println("类名="+this.getClass().getName());
        Class<?> e = find0(this, ParentUser.class, "E");

        System.out.println("泛型类名="+e.getClass().getName());

    }

    private static  Class<?> find0(Object object, Class<?> parametrizedSuperclass, String typeParamName) {
        Class<?> thisClass = object.getClass();
        Class currentClass = thisClass;

        do {
            while(currentClass.getSuperclass() != parametrizedSuperclass) {
                currentClass = currentClass.getSuperclass();
                if (currentClass == null) {
                    System.out.println("class null");
                    return null;
                }
            }

            int typeParamIndex = -1;
            TypeVariable<?>[] typeParams = currentClass.getSuperclass().getTypeParameters();

            for(int i = 0; i < typeParams.length; ++i) {
                if (typeParamName.equals(typeParams[i].getName())) {
                    typeParamIndex = i;
                    break;
                }
            }

            if (typeParamIndex < 0) {
                throw new IllegalStateException("unknown type parameter '" + typeParamName + "': " + parametrizedSuperclass);
            }

            Type genericSuperType = currentClass.getGenericSuperclass();
            if (!(genericSuperType instanceof ParameterizedType)) {
                return Object.class;
            }

            Type[] actualTypeParams = ((ParameterizedType)genericSuperType).getActualTypeArguments();
            Type actualTypeParam = actualTypeParams[typeParamIndex];
            if (actualTypeParam instanceof ParameterizedType) {
                actualTypeParam = ((ParameterizedType)actualTypeParam).getRawType();
            }

            if (actualTypeParam instanceof Class) {
                return (Class)actualTypeParam;
            }

            if (actualTypeParam instanceof GenericArrayType) {
                Type componentType = ((GenericArrayType)actualTypeParam).getGenericComponentType();
                if (componentType instanceof ParameterizedType) {
                    componentType = ((ParameterizedType)componentType).getRawType();
                }

                if (componentType instanceof Class) {
                    return Array.newInstance((Class)componentType, 0).getClass();
                }
            }

            TypeVariable<?> v = (TypeVariable)actualTypeParam;
            currentClass = thisClass;
            if (!(v.getGenericDeclaration() instanceof Class)) {
                return Object.class;
            }

            parametrizedSuperclass = (Class)v.getGenericDeclaration();
            typeParamName = v.getName();
        } while(parametrizedSuperclass.isAssignableFrom(thisClass));

        return Object.class;
    }





}

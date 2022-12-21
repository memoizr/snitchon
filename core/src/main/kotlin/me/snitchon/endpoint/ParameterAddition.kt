package me.snitchon.endpoint

import me.snitchon.router.Par

object ParameterAddition {
    context(Endpoint0<RETURN>)
    infix operator fun <
            ONE : HP,
            TWO : HP,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint1<A, RETURN>)
    infix operator fun <
            A : Par,
            ONE : HP,
            TWO : HP,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint2<A, B, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint3<A, B, C, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint4<A, B, C, D, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint5<A, B, C, D, E, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            E : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint6<A, B, C, D, E, F, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            E : Par,
            F : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint7<A, B, C, D, E, F, G, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            E : Par,
            F : Par,
            G : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint8<A, B, C, D, E, F, G, H, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            E : Par,
            F : Par,
            G : Par,
            H : Par,
            ONE : Par,
            TWO : Par,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)
}
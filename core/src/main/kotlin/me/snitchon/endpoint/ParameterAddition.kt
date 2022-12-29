package me.snitchon.endpoint

import me.snitchon.http.RequestWrapper
import me.snitchon.router.Par
import java.sql.Wrapper

object ParameterAddition {
    context(Endpoint0<W, RETURN>)
    infix operator fun <
            ONE : HP,
            TWO : HP,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint1<A, W, RETURN>)
    infix operator fun <
            A : Par,
            ONE : HP,
            TWO : HP,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint2<A, B, W, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            ONE : Par,
            TWO : Par,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint3<A, B, C, W, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            ONE : Par,
            TWO : Par,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint4<A, B, C, D, W, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            ONE : Par,
            TWO : Par,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint5<A, B, C, D, E, W, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            E : Par,
            ONE : Par,
            TWO : Par,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint6<A, B, C, D, E, F, W, RETURN>)
    infix operator fun <
            A : Par,
            B : Par,
            C : Par,
            D : Par,
            E : Par,
            F : Par,
            ONE : Par,
            TWO : Par,
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint7<A, B, C, D, E, F, G, W, RETURN>)
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
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)

    context(Endpoint8<A, B, C, D, E, F, G, H, W, RETURN>)
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
            W: RequestWrapper,
            RETURN : Any> ONE.plus(parameter: TWO) = with(this).with(parameter)
}
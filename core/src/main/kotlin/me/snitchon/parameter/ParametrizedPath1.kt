package me.snitchon.parameter

import me.snitchon.path.Path


data class ParametrizedPath0
    (val path: String)

data class ParametrizedPath1<
        P1 : Path<P1,*>
        >(val path: String, val p1: P1)

data class ParametrizedPath2<
        P1P,
        P1 : Path<P1, P1P>,
        P2P,
        P2 : Path<P2, P2P>
        >(val path: String, val p1: P1, val p2: P2)

data class ParametrizedPath3<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        >(val path: String, val p1: P1, val p2: P2, val p3: P3)

data class ParametrizedPath4<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        >(val path: String, val p1: P1, val p2: P2, val p3: P3, val p4: P4)


data class ParametrizedPath5<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        >(val path: String, val p1: P1, val p2: P2, val p3: P3, val p4: P4, val p5: P5)


data class ParametrizedPath6<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        >(val path: String, val p1: P1, val p2: P2, val p3: P3, val p4: P4, val p5: P5, val p6: P6)


data class ParametrizedPath7<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        >(val path: String, val p1: P1, val p2: P2, val p3: P3, val p4: P4, val p5: P5, val p6: P6, val p7: P7)


data class ParametrizedPath8<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8
)


data class ParametrizedPath9<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9
)


data class ParametrizedPath10<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10
)


data class ParametrizedPath11<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11
)


data class ParametrizedPath12<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12
)


data class ParametrizedPath13<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13
)


data class ParametrizedPath14<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14
)


data class ParametrizedPath15<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15
)


data class ParametrizedPath16<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16
)


data class ParametrizedPath17<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17
)


data class ParametrizedPath18<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18
)


data class ParametrizedPath19<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19
)


data class ParametrizedPath20<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20
)


data class ParametrizedPath21<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        P21 : Path<P21, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20,
    val p21: P21
)


data class ParametrizedPath22<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        P21 : Path<P21, *>,
        P22 : Path<P22, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20,
    val p21: P21,
    val p22: P22
)


data class ParametrizedPath23<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        P21 : Path<P21, *>,
        P22 : Path<P22, *>,
        P23 : Path<P23, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20,
    val p21: P21,
    val p22: P22,
    val p23: P23
)


data class ParametrizedPath24<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        P21 : Path<P21, *>,
        P22 : Path<P22, *>,
        P23 : Path<P23, *>,
        P24 : Path<P24, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20,
    val p21: P21,
    val p22: P22,
    val p23: P23,
    val p24: P24
)


data class ParametrizedPath25<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        P21 : Path<P21, *>,
        P22 : Path<P22, *>,
        P23 : Path<P23, *>,
        P24 : Path<P24, *>,
        P25 : Path<P25, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20,
    val p21: P21,
    val p22: P22,
    val p23: P23,
    val p24: P24,
    val p25: P25
)


data class ParametrizedPath26<
        P1 : Path<P1, *>,
        P2 : Path<P2, *>,
        P3 : Path<P3, *>,
        P4 : Path<P4, *>,
        P5 : Path<P5, *>,
        P6 : Path<P6, *>,
        P7 : Path<P7, *>,
        P8 : Path<P8, *>,
        P9 : Path<P9, *>,
        P10 : Path<P10, *>,
        P11 : Path<P11, *>,
        P12 : Path<P12, *>,
        P13 : Path<P13, *>,
        P14 : Path<P14, *>,
        P15 : Path<P15, *>,
        P16 : Path<P16, *>,
        P17 : Path<P17, *>,
        P18 : Path<P18, *>,
        P19 : Path<P19, *>,
        P20 : Path<P20, *>,
        P21 : Path<P21, *>,
        P22 : Path<P22, *>,
        P23 : Path<P23, *>,
        P24 : Path<P24, *>,
        P25 : Path<P25, *>,
        P26 : Path<P26, *>,
        >(
    val path: String,
    val p1: P1,
    val p2: P2,
    val p3: P3,
    val p4: P4,
    val p5: P5,
    val p6: P6,
    val p7: P7,
    val p8: P8,
    val p9: P9,
    val p10: P10,
    val p11: P11,
    val p12: P12,
    val p13: P13,
    val p14: P14,
    val p15: P15,
    val p16: P16,
    val p17: P17,
    val p18: P18,
    val p19: P19,
    val p20: P20,
    val p21: P21,
    val p22: P22,
    val p23: P23,
    val p24: P24,
    val p25: P25,
    val p26: P26
)

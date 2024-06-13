package com.appsbig.patihuaniapp

import kotlin.random.Random

class EvolucionDiferencial(
    private val tamanoPoblacion: Int,
    private val numeroVariables: Int,
    private val factorMutacion: Double,
    private val tasaCruzamiento: Double,
    private val limitesInferiores: DoubleArray,
    private val limitesSuperiores: DoubleArray,
    private val generaciones: Int,
    private val epsilon: Double
) {
    data class Solucion(val variables: DoubleArray, val aptitud: Double, val restricciones: DoubleArray, val svr: Double)

    private lateinit var poblacion: Array<Solucion>
    private var energiaDiaria: Double = 0.0
    private var proteinasDiarias: Double = 0.0
    private var carbohidratosDiarios: Double = 0.0

    fun setNecesidadesNutricionales(energia: Double, proteinas: Double, carbohidratos: Double) {
        this.energiaDiaria = energia
        this.proteinasDiarias = proteinas
        this.carbohidratosDiarios = carbohidratos
    }

    fun optimizar(): Solucion {
        poblacion = inicializarPoblacion()
        var mejorSolucion = poblacion.minByOrNull { it.aptitud }!!

        for (gen in 1..generaciones) {
            val nuevaPoblacion = Array(tamanoPoblacion) { poblacion[it].copy() }
            for (j in 0 until tamanoPoblacion) {
                val (mutante, prueba) = mutarYCruzarse(poblacion, j)
                val solucionPrueba = evaluarSolucion(prueba)

                nuevaPoblacion[j] = aplicarReglasDEB(poblacion[j], solucionPrueba)
            }
            poblacion = nuevaPoblacion
            val mejorActual = poblacion.minByOrNull { it.aptitud }!!
            if (mejorActual.aptitud < mejorSolucion.aptitud) {
                mejorSolucion = mejorActual
            }
        }

        return mejorSolucion
    }

    private fun inicializarPoblacion(): Array<Solucion> {
        return Array(tamanoPoblacion) {
            val variables = DoubleArray(numeroVariables) { i -> limitesInferiores[i] + (limitesSuperiores[i] - limitesInferiores[i]) * Random.nextDouble() }
            val solucion = evaluarSolucion(variables)
            println("Inicializado: ${solucion.variables.joinToString(", ")} con aptitud: ${solucion.aptitud}")
            solucion
        }
    }

    private fun evaluarSolucion(variables: DoubleArray): Solucion {
        val variablesReparadas = variables.map { repararValor(it, 0.0, Double.MAX_VALUE) }.toDoubleArray()
        val aptitud = funcionObjetivo(variablesReparadas)
        val restriccionesG = calcularRestriccionesG(variablesReparadas)
        val restriccionesH = calcularRestriccionesH(variablesReparadas, epsilon)
        val svr = restriccionesG.sum() + restriccionesH.sum()
        return Solucion(variablesReparadas, aptitud, restriccionesG + restriccionesH, svr)
    }

    private fun mutarYCruzarse(poblacion: Array<Solucion>, indiceActual: Int): Pair<DoubleArray, DoubleArray> {
        val indices = (poblacion.indices - indiceActual).shuffled().take(3)
        val (a, b, c) = indices.map { poblacion[it].variables }

        val mutante = DoubleArray(numeroVariables) { i -> repararValor(a[i] + factorMutacion * (b[i] - c[i]), limitesInferiores[i], limitesSuperiores[i]) }

        val prueba = poblacion[indiceActual].variables.copyOf()
        val jrand = Random.nextInt(numeroVariables)
        prueba[jrand] = mutante[jrand]
        for (k in 0 until numeroVariables) {
            if (Random.nextDouble() <= tasaCruzamiento || k == jrand) {
                prueba[k] = mutante[k]
            }
        }
        return Pair(mutante, prueba)
    }

    private fun repararValor(valor: Double, minimo: Double, maximo: Double): Double {
        return minOf(maxOf(valor, minimo), maximo)
    }

    private fun aplicarReglasDEB(solucionActual: Solucion, solucionPrueba: Solucion): Solucion {
        return when {
            solucionActual.restricciones.all { it <= 0 } && solucionPrueba.restricciones.all { it <= 0 } -> {
                if (solucionPrueba.aptitud < solucionActual.aptitud) solucionPrueba else solucionActual
            }
            solucionPrueba.restricciones.all { it <= 0 } -> solucionPrueba
            solucionActual.restricciones.any { it > 0 } -> {
                if (solucionPrueba.restricciones.any { it > 0 } && solucionPrueba.svr < solucionActual.svr) solucionPrueba else solucionActual
            }
            else -> solucionActual
        }
    }

    private fun funcionObjetivo(x: DoubleArray): Double {
        val costos = doubleArrayOf(2.5)
        val resultado = x.zip(costos).sumOf { it.first * it.second }
        return resultado
    }

    private fun calcularRestriccionesG(x: DoubleArray): DoubleArray {
        val energia = doubleArrayOf(500.0)
        val proteinas = doubleArrayOf(30.0)
        val carbohidratos = doubleArrayOf(50.0)

        val energiaTotal = x.zip(energia).sumOf { it.first * it.second }
        val proteinasTotal = x.zip(proteinas).sumOf { it.first * it.second }
        val carbohidratosTotal = x.zip(carbohidratos).sumOf { it.first * it.second }

        val g1 = energiaDiaria - energiaTotal
        val g2 = proteinasDiarias - proteinasTotal
        val g3 = carbohidratosDiarios - carbohidratosTotal

        val restricciones = doubleArrayOf(g1, g2, g3)

        return restricciones
    }

    private fun calcularRestriccionesH(x: DoubleArray, ep: Double): DoubleArray {
        return DoubleArray(0)
    }

    private fun DoubleArray.sum(): Double {
        return if (this.isEmpty()) 0.0 else this.reduce { acc, d -> acc + d }
    }

}

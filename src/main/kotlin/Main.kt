class ParkingLot {
    private var parkingLotSize: Int = 0
    private var motorcycleSpots: Int = 0
    private var carSpots: Int = 0
    private var vanSpots: Int = 0
    private var occupiedMotorcycleSpots: Int = 0
    private var occupiedCarSpots: Int = 0
    private var occupiedVanSpots: Int = 0
    private var vanSpotOccupiedByCar: Int = 0
    private var vanSpotOccupiedByMotorcycle: Int = 0
    private var carSpotOccupiedByMotorcycle: Int = 0
    private var carSpotOccupiedByVan: Int = 0

    fun startParkingLot() {
        println("Bem-vindo ao sistema de estacionamento!")
        println("Informe o tamanho do estacionamento (em vagas):")
        parkingLotSize = readLine()?.toIntOrNull() ?: 0

        println("Informe a quantidade de vagas para motos:")
        motorcycleSpots = readLine()?.toIntOrNull() ?: 0

        println("Informe a quantidade de vagas para carros:")
        carSpots = readLine()?.toIntOrNull() ?: 0

        println("Informe a quantidade de vagas para vans:")
        vanSpots = readLine()?.toIntOrNull() ?: 0

        if (motorcycleSpots + carSpots + vanSpots > parkingLotSize) {
            println("O número de vagas informado é maior que o tamanho do estacionamento.")
            startParkingLot()
        } else {
            showMenu()
        }
    }

    private fun showMenu() {
        println("----- Estacionamento -----")
        println("A - Estacionar veículo")
        println("B - Fazer a saída do veículo")
        println("C - Exibir vagas ocupadas")
        println("D - Exibir vagas disponíveis")
        println("E - Exibir tamanho total do estacionamento")
        println("F - Resetar Estacionamento")
        println("G - Sair do Sistema")
        println("Escolha uma opção:")

        when (readlnOrNull()?.uppercase()) {
            "A" -> parkVehicle()
            "B" -> removeVehicle()
            "C" -> showOccupiedSpots()
            "D" -> showAvailableSpots()
            "E" -> showParkingLotSize()
            "F" -> resetParkingLot()
            "G" -> {
                println("Você está saindo do sistema. Até mais!")
                return
            }

            else -> {
                println("Opção inválida. Escolha uma opção válida.")
                showMenu()
            }
        }
    }

    private fun parkVehicle() {
        println("Escolha a categoria do veículo:")
        println("1 - Moto")
        println("2 - Carro")
        println("3 - Van")

        when (readLine()) {
            "1" -> parkMotorcycle()
            "2" -> parkCar()
            "3" -> parkVan()
            else -> {
                println("Opção inválida. Escolha uma opção válida.")
                parkVehicle()
            }
        }
    }

    private fun parkCar() {
        if (carSpots > 0) {
            carSpots--
            occupiedCarSpots++
            println("Carro estacionado com sucesso!")
        } else if (vanSpots > 0 && vanSpotOccupiedByCar == 0) {
            vanSpots--
            occupiedVanSpots++
            vanSpotOccupiedByCar++
            println("Carro estacionado em uma vaga de van com sucesso!")
        } else {
            println("Não há vagas disponíveis para carros.")
        }

        showMenu()
    }


    private fun parkMotorcycle() {
        when {
            motorcycleSpots > 0 -> {
                motorcycleSpots--
                occupiedMotorcycleSpots++
                println("Moto estacionada com sucesso!")
            }

            carSpots > 0 -> {
                carSpots--
                carSpotOccupiedByMotorcycle++
                occupiedCarSpots++
                println("Moto estacionada em uma vaga de carro!")
            }

            vanSpots > 0 -> {
                vanSpots--
                vanSpotOccupiedByMotorcycle++
                occupiedVanSpots++
                println("Moto estacionada em uma vaga de van!")
            }

            else -> println("Estacionamento lotado. Não há vagas disponíveis.")
        }

        showMenu()
    }

    private fun parkVan() {
        when {
            vanSpots > 0 -> {
                vanSpots--
                occupiedVanSpots++
                println("Van estacionada com sucesso!")
            }

            carSpots >= 3 -> {
                carSpots -= 3
                occupiedCarSpots += 3
                carSpotOccupiedByVan += 3
                println("Van estacionada com sucesso em três vagas de carro!")
            }

            else -> println("O estacionamento está lotado.")
        }

        showMenu()
    }

    private fun removeVehicle() {
        println("Escolha a categoria do veículo:")
        println("1 - Moto")
        println("2 - Carro")
        println("3 - Van")

        when (readLine()) {
            "1" -> removeMotorcycle()
            "2" -> removeCar()
            "3" -> removeVan()
            else -> {
                println("Opção inválida. Escolha uma opção válida.")
                removeVehicle()
            }
        }
    }

    private fun removeMotorcycle() {
        when {
            occupiedMotorcycleSpots > 0 -> {
                occupiedMotorcycleSpots--
                motorcycleSpots++
            }

            carSpotOccupiedByMotorcycle > 0 -> {
                carSpotOccupiedByMotorcycle--
                occupiedCarSpots--
                carSpots++
            }

            vanSpotOccupiedByMotorcycle > 0 -> {
                vanSpotOccupiedByMotorcycle--
                occupiedVanSpots--
                vanSpots++
            }

            else -> println("Não há motos estacionadas no momento.")
        }

        println("Moto removida com sucesso!")
        showMenu()
    }


    private fun removeCar() {
        if (occupiedCarSpots > 0) {
            occupiedCarSpots--
            carSpots++
            println("Carro removido com sucesso!")
        } else if (vanSpotOccupiedByCar > 0) {
            vanSpotOccupiedByCar--
            occupiedVanSpots--
            vanSpots++
            println("Carro removido com sucesso!")
        } else {
            println("Não há carros estacionados no momento.")
        }

        showMenu()
    }

    private fun removeVan() {
        if (occupiedVanSpots > 0) {
            vanSpots += occupiedVanSpots
            carSpots += carSpotOccupiedByVan / 3
            occupiedCarSpots -= carSpotOccupiedByVan
            carSpotOccupiedByVan = 0
            occupiedVanSpots = 0
            println("Van removida com sucesso!")
        } else {
            println("Não há vans estacionadas.")
        }

        showMenu()
    }

    private fun showOccupiedSpots() {
        println("Vagas ocupadas:")
        println("Motos: $occupiedMotorcycleSpots")
        println("Carros: $occupiedCarSpots")
        println("Vans: $occupiedVanSpots")

        showMenu()
    }

    private fun showAvailableSpots() {
        println("Vagas disponíveis:")
        println("Motos: $motorcycleSpots")
        println("Carros: $carSpots")
        println("Vans: $vanSpots")

        showMenu()
    }

    private fun showParkingLotSize() {
        println("Tamanho total do estacionamento: $parkingLotSize vagas")

        showMenu()
    }

    private fun resetParkingLot() {
        motorcycleSpots = 0
        carSpots = 0
        vanSpots = 0
        occupiedMotorcycleSpots = 0
        occupiedCarSpots = 0
        occupiedVanSpots = 0
        vanSpotOccupiedByCar = 0
        vanSpotOccupiedByMotorcycle = 0
        carSpotOccupiedByMotorcycle = 0
        carSpotOccupiedByVan = 0

        println("Estacionamento resetado com sucesso!")

        startParkingLot()
    }
}

fun main() {
    val parking = ParkingLot()
    parking.startParkingLot()
}

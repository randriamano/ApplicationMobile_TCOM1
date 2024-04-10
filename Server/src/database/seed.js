const { PrismaClient } = require('@prisma/client')

const prisma = new PrismaClient()

const adminData = [
  {
    name: 'Admin',
    key: 'admin',
  },
]

const productData = [
  {
    productName: "Polo",
    productPrice: "21 000",
    productDescription: "Polo avec les logos TELECOM et ESPA conçu par le GET",
    productCategory: "Vêtements",
    availableColorList: [ 'Rouge', 'Vert' ],
    availableSizeList: [ 'M', 'L', 'XL' ],
    productImageURLList: [ "/images/polo", '/images/polo/1', '/images/polo/2', ],
    productRemainingStock: 5,
  },
]

const studentData = [
  {
    cardNum: "013-24/M1/TCO",
    name: "RANDRIANTSIROFO",
    firstname: " Thiarson Antsa Fanantenanana",
    password: "123456",
  },
]

async function main() {
  console.log(`Start seeding ...`)

  for (const data of adminData) {
    const admin = await prisma.admin.create({
      data: data,
    })
    console.log(`Created admin with key: ${admin.key}`)
  }

  for (const data of productData) {
    const product = await prisma.product.create({
      data: data,
    })
    console.log(`Created product with name: ${product.name}`)
  }

  for (const data of studentData) {
    const student = await prisma.student.create({
      data: data,
    })
    console.log(`Created student with card number: ${student.cardNum}`)
  }

  console.log(`Seeding finished.`)
}

main()
  .then(async () => {
    await prisma.$disconnect()
  })
  .catch(async (e) => {
    console.error(e)
    await prisma.$disconnect()
    process.exit(1)
  })

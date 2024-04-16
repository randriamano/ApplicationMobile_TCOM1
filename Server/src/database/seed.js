const { PrismaClient } = require("@prisma/client");

const prisma = new PrismaClient();

const adminData = [
  {
    studentCardNum: "025-M1",
    adminPassword: "123456",
    adminKey: "admin",
  },
];

const categories = [ 'vetements', 'billets', 'goodies' ]

const productData = [
  {
    productName:"T-shirt",
    productPrice:"21 000",
    productDescription:" Polo avec Logo Get conçu par le GET 2024",
    productCategory:"vetements",
    availableColorList:['Navy Blue','White'],
    availableSizeList:['S','M','L','XL','3XL'],
    productImageURLList:[ "/images/tshirt/0", "/images/tshirt/1", "/images/tshirt/2", ],
    productRemainingStock:15,
  },
  {
    productName:"Parapluie",
    productPrice:"30 000",
    productDescription:" Parapluie avec Logo Get conçu par le GET 2024",
    productCategory:"goodies",
    availableColorList:['Navy Blue',],
    availableSizeList:['XL'],
    productImageURLList:["/images/parapluie/0" ],
    productRemainingStock:10,
  },
  {
    productName:"Tote Bag",
    productPrice:"15 000",
    productDescription:" Tote Bag avec Logo Get conçu par le GET 2024",
    productCategory:"goodies",
    availableColorList:['Navy Blue'],
    availableSizeList:['XL'],
    productImageURLList:[ "/images/totebag/0", "/images/totebag/1", "/images/totebag/2", "/images/totebag/3", ],
    productRemainingStock:20,
  },
  {
    productName:"Porte-Clés",
    productPrice:"5000",
    productDescription:" Porte-Clés Logo Get conçu par le GET 2024",
    productCategory:"goodies",
    availableColorList:["White"],
    availableSizeList:["L"],
    productImageURLList:[ "/images/portecles/0", "/images/portecles/1", ],
    productRemainingStock:50,
  }
]

const studentData = [
  {
    studentCardNum: "013-M1",
    studentName: "RANDRIANTSIROFO",
    studentFirstname: "Thiarson Antsa",
    studentPassword: "123456",
  },
  {
    studentCardNum: "011-M1",
    studentName: "RAMAROKOTO",
    studentFirstname: "Tommy",
    studentPassword: "123456",
  },
]

const commandedProduct = [
  {
    productId: 1,
    studentId: 2,
    productSizeChosen: "L",
    productColorChosen: "White", 
  },
  {
    productId: 2,
    studentId: 1,
    productSizeChosen: "XL",
    productColorChosen: "Navy Blue", 
  },
]

async function main() {
  console.log(`Start seeding ...`);

  for (const data of adminData) {
    const admin = await prisma.admin.create({
      data: data,
    });
    console.log(`Created admin with key: ${admin.adminKey}`);
  }

  for (const data of productData) {
    const product = await prisma.product.create({
      data: data,
    });
    console.log(`Created product with name: ${product.productName}`);
  }

  for (const data of studentData) {
    const student = await prisma.student.create({
      data: data,
    });
    console.log(`Created student with card number: ${student.studentCardNum}`);
  }

  for (const data of commandedProduct) {
    const command = await prisma.command.create({
      data: data,
    });
    console.log(`Created command`);
  }

  console.log(`Seeding finished.`);
}

main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });

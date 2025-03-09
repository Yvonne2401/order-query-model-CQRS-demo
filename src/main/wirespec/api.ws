type OrderDTO{
  orderId: Number,
  totalAmount: Number,
  amountPaid: Number
}

endpoint OrderDTO GET /orders -> {
    200 -> OrderDTO[]
}
